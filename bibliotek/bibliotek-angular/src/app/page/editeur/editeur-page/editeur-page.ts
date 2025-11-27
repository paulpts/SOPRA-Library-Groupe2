import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { EditeurDto } from '../../../dto/editeur-dto';
import { EditeurService } from '../../../service/editeur-service';
import { Observable } from 'rxjs';
import { NationalitePipe } from '../../../pipe/nationalite-pipe';
import { NATIONALITE_LABEL } from '../../../enumerator/nationalite-label';

@Component({
  imports: [ CommonModule, RouterLink, ReactiveFormsModule, NationalitePipe ],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit {
  protected editeurs$!: Observable<EditeurDto[]>;

  protected showForm: boolean = false;
  protected editeurForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected paysCtrl!: FormControl;
  protected nationalites = Object.keys(NATIONALITE_LABEL);
  protected editingEditeur!: EditeurDto | null;

  constructor(private editeurService: EditeurService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.editeurs$ = this.editeurService.findAll();

    this.nameCtrl = this.formBuilder.control('', Validators.required);
    this.paysCtrl = this.formBuilder.control('', Validators.required);

    this.editeurForm = this.formBuilder.group({
      name: this.nameCtrl,
      pays: this.paysCtrl
    });
  }

  public trackEditeur(index: number, value: EditeurDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingEditeur) {
      this.editeurService.save(new EditeurDto(this.editingEditeur.id, this.nameCtrl.value, this.paysCtrl.value));
    }

    else {
      this.editeurService.save(new EditeurDto("", this.nameCtrl.value, this.paysCtrl.value));
    }

    this.showForm = false;
    this.editingEditeur = null;
    this.editeurForm.reset();
  }

  public editer(editeur: EditeurDto) {
    this.editingEditeur = editeur;
    this.showForm = true;

    this.nameCtrl.setValue(editeur.nom);
    this.paysCtrl.setValue(editeur.pays);
  }

  public annulerEditer() {
    this.editingEditeur = null;
    this.editeurForm.reset();
    this.showForm = false;
  }

  public supprimer(editeur: EditeurDto) {
    this.editeurService.deleteById(editeur.id);
  }
}
