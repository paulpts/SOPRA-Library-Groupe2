import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { AuteurDto } from '../../../dto/auteur-dto';
import { AuteurService } from '../../../service/auteur-service';
import { NationalitePipe } from '../../../pipe/nationalite-pipe';
import { NATIONALITE_LABEL } from '../../../enumerator/nationalite-label';

@Component({
  imports: [ CommonModule, RouterLink, ReactiveFormsModule, NationalitePipe ],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage implements OnInit {
  protected auteurs$!: Observable<AuteurDto[]>;

  protected showForm: boolean = false;
  protected auteurForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected firstnameCtrl!: FormControl;
  protected nationalityCtrl!: FormControl;
  protected nationalites = Object.keys(NATIONALITE_LABEL);
  protected editingAuteur!: AuteurDto | null;

  constructor(private auteurService: AuteurService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.auteurs$ = this.auteurService.findAll();

    this.nameCtrl = this.formBuilder.control('', Validators.required);
    this.firstnameCtrl = this.formBuilder.control('', Validators.required);
    this.nationalityCtrl = this.formBuilder.control('', Validators.required);

    this.auteurForm = this.formBuilder.group({
      name: this.nameCtrl,
      firstname: this.firstnameCtrl,
      nationality: this.nationalityCtrl
    });
  }

  public trackAuteur(index: number, value: AuteurDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingAuteur) {
      this.auteurService.save(new AuteurDto(this.editingAuteur.id, this.nameCtrl.value, this.firstnameCtrl.value, this.nationalityCtrl.value));
    }

    else {
      this.auteurService.save(new AuteurDto("", this.nameCtrl.value, this.firstnameCtrl.value, this.nationalityCtrl.value));
    }

    this.showForm = false;
    this.editingAuteur = null;
    this.auteurForm.reset();
  }

  public editer(auteur: AuteurDto) {
    this.editingAuteur = auteur;
    this.showForm = true;

    this.nameCtrl.setValue(auteur.nom);
    this.firstnameCtrl.setValue(auteur.prenom);
    this.nationalityCtrl.setValue(auteur.nationalite);
  }

  public annulerEditer() {
    this.editingAuteur = null;
    this.auteurForm.reset();
    this.showForm = false;
  }

  public supprimer(auteur: AuteurDto) {
    this.auteurService.deleteById(auteur.id);
  }
}
