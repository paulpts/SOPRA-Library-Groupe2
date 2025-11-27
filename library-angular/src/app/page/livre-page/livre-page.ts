import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { LivreDto } from '../../dto/livre-dto';
import { LivreService } from '../../service/livre-service';
import { RouterLink } from "@angular/router";
import { AuteurService } from '../../service/auteur-service';
import { AuteurDto } from '../../dto/dto-auteur';
import { CollectionService } from '../../service/collection-service';
import { EditeurService } from '../../service/editeur-service';
import { GenreService } from '../../service/genre-service';
import { EditeurDto } from '../../dto/editeur-dto';
import { CollectionDto } from '../../dto/collection-dto';
import { GenreDto } from '../../dto/genre-dto';



@Component({
  selector: 'app-livre-page',
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  
  protected livre$!: Observable<LivreDto[]>;
  
  protected livreForm!: FormGroup;
  protected titreCtrl!: FormControl;
  protected resumerCtrl!: FormControl;
  protected anneeCtrl!: FormControl;
  protected auteurCtrl!: FormControl;
  protected editeurCtrl!: FormControl;
  protected collectionCtrl!: FormControl;
  protected genreCtrl!: FormControl;
  
  protected editingLivre!:LivreDto | null;

  
  constructor(private formBuilder: FormBuilder, private livreSrv: LivreService, private auteurSrv:AuteurService, private collectionSrv: CollectionService, private editeurSrv:EditeurService, private genreSrv: GenreService){}
  
  protected auteur$!: AuteurDto[];
  protected editeur$!: EditeurDto[];
  protected collection$!: CollectionDto[];
  protected genre$!: GenreDto[];

  ngOnInit(): void {
    
    this.livre$ = this.livreSrv.findAll();
    
    this.titreCtrl = this.formBuilder.control('',Validators.required);
    this.resumerCtrl = this.formBuilder.control('',[Validators.required,Validators.maxLength(300)]);
    this.anneeCtrl = this.formBuilder.control('',Validators.required);
    this.auteurCtrl = this.formBuilder.control('',Validators.required);
    this.editeurCtrl = this.formBuilder.control('',Validators.required);
    this.collectionCtrl = this.formBuilder.control('',Validators.required);
    this.genreCtrl = this.formBuilder.control('',Validators.required);
    
    this.livreForm=this.formBuilder.group({ 
      titre:this.titreCtrl,
      resumer:this.resumerCtrl,
      annee:this.anneeCtrl,
      auteur:this.auteurCtrl,
      editeur:this.editeurCtrl,
      collection:this.collectionCtrl,
      genre:this.genreCtrl
    });


      this.auteurSrv.findAll().subscribe({
    next: data => this.auteur$ = data,
    error: err => console.error(err)
  });

        this.editeurSrv.findAll().subscribe({
    next: data => this.editeur$ = data,
    error: err => console.error(err)
  });

        this.collectionSrv.findAll().subscribe({
    next: data => this.collection$ = data,
    error: err => console.error(err)
  });

        this.genreSrv.findAll().subscribe({
    next: data => this.genre$ = data,
    error: err => console.error(err)
  });
  }
  
  public trackLivre(index: number, value: LivreDto) {
    return value.id;
  }
  

  //------------------------------------------------------------------
  public creerModifier() {
    if (this.editingLivre) {
      this.livreSrv.save(new LivreDto(
        this.editingLivre.id, 
        this.titreCtrl.value,
        this.resumerCtrl.value,
        this.anneeCtrl.value,
        this.auteurCtrl.value,
        this.editeurCtrl.value,
        this.collectionCtrl.value,
        this.genreCtrl.value
      ));
    }
    
    else {
      this.livreSrv.save(new LivreDto(0, 
        this.titreCtrl.value,
        this.resumerCtrl.value,
        this.anneeCtrl.value,
        this.auteurCtrl.value,
        this.editeurCtrl.value,
        this.collectionCtrl.value,
        this.genreCtrl.value
      ));
    }
    
    this.editingLivre = null;
    this.titreCtrl.setValue("");
    this.resumerCtrl.setValue("");
    this.anneeCtrl.setValue("");
    this.auteurCtrl.setValue("");
    this.editeurCtrl.setValue("");
    this.collectionCtrl.setValue("");
    this.genreCtrl.setValue("");
  }
  
//------------------------------------------------------------------


  public editerLivre(livre: LivreDto){
    this.editingLivre=livre;
    this.titreCtrl.setValue(livre.titre);
    this.resumerCtrl.setValue(livre.resumer);
    this.anneeCtrl.setValue(livre.annee);
    this.auteurCtrl.setValue(livre.auteur.id);
    this.editeurCtrl.setValue(livre.editeur.id);
    this.collectionCtrl.setValue(livre.collection.id);
    this.genreCtrl.setValue(livre.genre.id);
    
  }
  
  public supprimerLivre(livre: LivreDto){
    this.livreSrv.deleteById(livre.id);
  }
  
}
