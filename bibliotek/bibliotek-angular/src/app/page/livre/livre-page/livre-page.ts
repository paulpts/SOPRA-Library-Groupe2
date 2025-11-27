import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { LivreDto } from '../../../dto/livre-dto';
import { LivreService } from '../../../service/livre-service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuteurDto } from '../../../dto/auteur-dto';
import { AuteurService } from '../../../service/auteur-service';
import { CollectionDto } from '../../../dto/collection-dto';
import { EditeurDto } from '../../../dto/editeur-dto';
import { EditeurService } from '../../../service/editeur-service';
import { CollectionService } from '../../../service/collection-service';

@Component({
  imports: [ CommonModule, RouterLink, ReactiveFormsModule ],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  protected livres$!: Observable<LivreDto[]>;
  protected auteurs$!: Observable<AuteurDto[]>;
  protected editeurs$!: Observable<EditeurDto[]>;
  protected collections$!: Observable<CollectionDto[]>;

  protected showForm: boolean = false;
  protected livreForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected publicationCtrl!: FormControl;
  protected authorCtrl!: FormControl;
  protected editorCtrl!: FormControl;
  protected collectionCtrl!: FormControl;
  protected editingLivre!: LivreDto | null;

  constructor(
    private livreService: LivreService,
    private auteurService: AuteurService,
    private editeurService: EditeurService,
    private collectionService: CollectionService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.livres$ = this.livreService.findAll();
    this.auteurs$ = this.auteurService.findAll();
    this.editeurs$ = this.editeurService.findAll();
    this.collections$ = this.collectionService.findAll();

    this.nameCtrl = this.formBuilder.control('', Validators.required);
    this.publicationCtrl = this.formBuilder.control('', [ Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/) ]);
    this.authorCtrl = this.formBuilder.control('', Validators.required);
    this.editorCtrl = this.formBuilder.control('', Validators.required);
    this.collectionCtrl = this.formBuilder.control('');

    this.livreForm = this.formBuilder.group({
      name: this.nameCtrl,
      publication: this.publicationCtrl,
      author: this.authorCtrl,
      editor: this.editorCtrl,
      collection: this.collectionCtrl
    });
  }

  public trackLivre(index: number, value: LivreDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingLivre) {
      this.livreService.save(new LivreDto(this.editingLivre.id, this.nameCtrl.value, this.publicationCtrl.value, this.authorCtrl.value, "", this.editorCtrl.value, "", this.collectionCtrl.value));
    }

    else {
      this.livreService.save(new LivreDto("", this.nameCtrl.value, this.publicationCtrl.value, this.authorCtrl.value, "", this.editorCtrl.value, "", this.collectionCtrl.value));
    }

    this.showForm = false;
    this.editingLivre = null;
    this.livreForm.reset();
  }

  public editer(livre: LivreDto) {
    this.editingLivre = livre;
    this.showForm = true;

    this.nameCtrl.setValue(livre.nom);
    this.publicationCtrl.setValue(livre.publication);
    this.authorCtrl.setValue(livre.auteurId);
    this.editorCtrl.setValue(livre.editeurId);
    this.collectionCtrl.setValue(livre.collectionId);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingLivre = null;
    this.livreForm.reset();
  }

  public supprimer(livre: LivreDto) {
    this.livreService.deleteById(livre.id);
  }
}
