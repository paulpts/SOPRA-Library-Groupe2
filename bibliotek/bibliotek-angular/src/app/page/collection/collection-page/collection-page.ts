import { Component, OnInit } from '@angular/core';
import { CollectionDto } from '../../../dto/collection-dto';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { CollectionService } from '../../../service/collection-service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  imports: [ CommonModule, RouterLink, ReactiveFormsModule ],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit {
  protected collections$!: Observable<CollectionDto[]>;

  protected showForm: boolean = false;
  protected collectionForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected editingCollection!: CollectionDto | null;

  constructor(private collectionService: CollectionService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.collections$ = this.collectionService.findAll();

    this.nameCtrl = this.formBuilder.control('', Validators.required);

    this.collectionForm = this.formBuilder.group({
      name: this.nameCtrl
    });
  }

  public trackCollection(index: number, value: CollectionDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingCollection) {
      this.collectionService.save(new CollectionDto(this.editingCollection.id, this.nameCtrl.value));
    }

    else {
      this.collectionService.save(new CollectionDto("", this.nameCtrl.value));
    }

    this.showForm = false;
    this.editingCollection = null;
    this.collectionForm.reset();
  }

  public editer(collection: CollectionDto) {
    this.editingCollection = collection;
    this.showForm = true;

    this.nameCtrl.setValue(collection.nom);
  }

  public annulerEditer() {
    this.editingCollection = null;
    this.collectionForm.reset();
    this.showForm = false;
  }

  public supprimer(collection: CollectionDto) {
    this.collectionService.deleteById(collection.id);
  }
}
