import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuteurDto } from '../../../../dto/dto-auteur';
import { AuteurService } from '../../../../service/auteur-service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-auteur-page',
  standalone: true,
  imports: [CommonModule,RouterLink,ReactiveFormsModule],
  templateUrl: './auteur-page.html',
  styleUrls: ['./auteur-page.css'],
})
export class AuteurPage {

  protected auteurs$!: Observable<AuteurDto[]>;
  protected auteurForm!: FormGroup;
  protected nomCtrl!: FormControl;
  protected prenomCtrl!: FormControl;
  protected nationaliteCtrl!: FormControl;
  protected editingAuteur!: AuteurDto | null;

  constructor(private auteurService: AuteurService, private formBuilder: FormBuilder) {}

  ngOnInit(): void { // permet d'afficher ce qu'on veut en premier donc la liste des auteurs

    this.nomCtrl = this.formBuilder.control('',Validators.required);
    this.prenomCtrl = this.formBuilder.control('',Validators.required);
    this.nationaliteCtrl = this.formBuilder.control('',Validators.required);
  
    this.auteurForm = this.formBuilder.group({
      nom: this.nomCtrl,
      prenom: this.prenomCtrl,
      nationalite: this.nationaliteCtrl
    });
      this.auteurs$ = this.auteurService.findAll();
  }

  public creerOuModifier() {

    if(this.editingAuteur) {
        this.auteurService.save(
          new AuteurDto(
            this.editingAuteur.id,
            this.nomCtrl.value,
            this.prenomCtrl.value,
            this.nationaliteCtrl.value
          )
        );
    } else {

      this.auteurService.save(
        new AuteurDto(
          0,
          this.nomCtrl.value,
          this.prenomCtrl.value,
          this.nationaliteCtrl.value
        )
      );
    }
    this.editingAuteur=null;
    this.auteurForm.reset();
  }
  public trackAuteur(index: number, value:AuteurDto) {   // c'est pour optimiser et juste changer l'ID concerné et pas toute la page
    return value.id;
  }

  public editer(auteur: AuteurDto) {

    this.editingAuteur = auteur;
    this.nomCtrl.setValue(auteur.nom);
    this.prenomCtrl.setValue(auteur.prenom);
    this.nationaliteCtrl.setValue(auteur.nationalite);
  }

  public supprimer(auteur: AuteurDto) {

    this.auteurService.deleteById(auteur.id);
  }

}
