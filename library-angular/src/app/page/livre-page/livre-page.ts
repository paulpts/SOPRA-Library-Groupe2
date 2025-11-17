import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-livre-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  
  
  protected livreForm!: FormGroup;
  protected titreCtrl!: FormControl;
  protected resumerCtrl!: FormControl;
  protected anneeCtrl!: FormControl;
  protected auteurCtrl!: FormControl;
  protected editeurCtrl!: FormControl;
  protected collectionCtrl!: FormControl;
  protected genreCtrl!: FormControl;
  
  constructor(private formBuilder: FormBuilder){}
  
  ngOnInit(): void {



    this.titreCtrl = this.formBuilder.control('',Validators.required);
    this.resumerCtrl = this.formBuilder.control('',[Validators.required,Validators.maxLength(300)]);
    this.anneeCtrl = this.formBuilder.control('',Validators.required);
    this.auteurCtrl = this.formBuilder.control('',Validators.required);
    this.editeurCtrl = this.formBuilder.control('',Validators.required);
    this.collectionCtrl = this.formBuilder.control('',Validators.required);
    this.genreCtrl = this.formBuilder.control('',Validators.required);
    
    this.livreForm=this.formBuilder.group({
      titre:this.titreCtrl,
      resumer:this.titreCtrl,
      annee:this.titreCtrl,
      auteur:this.titreCtrl,
      editeur:this.titreCtrl,
      collection:this.titreCtrl,
      genre:this.titreCtrl
    });
  }
  
}
