import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CollectionDto } from '../../dto/collection-dto';
import { Observable } from 'rxjs';
import { CollectionService } from '../../service/collection-service';
import { FormControl, FormBuilder, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-collection',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './collection.html',
  styleUrl: './collection.css',
})
export class Collection implements OnInit{

  protected editingCollection!: CollectionDto | null;
  protected collections$!: Observable<CollectionDto[]>;
  protected labelCtrl!: FormControl;
  protected collectionForm!: FormGroup;

  constructor(private collectionService: CollectionService,  private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.collections$ = this.collectionService.findAll();
     this.labelCtrl = this.formBuilder.control('', Validators.required);

    this.collectionForm = this.formBuilder.group({
      label: this.labelCtrl
    });
  }


  public creerOuModifier() {
    if (this.editingCollection) {
      this.collectionService.save(new CollectionDto(this.editingCollection.id, this.labelCtrl.value));
    }

    else {
      this.collectionService.save(new CollectionDto(0, this.labelCtrl.value));
    }

    this.editingCollection = null;
    this.labelCtrl.setValue("");
  }

  public editer(collection: CollectionDto) {
    this.editingCollection = collection;
    this.labelCtrl.setValue(collection.nom);
  }

  public supprimer(collection: CollectionDto) {
    this.collectionService.deleteById(collection.id);
  }

    public trackCollection(index: number, value: CollectionDto) {
    return value.id;
  }

}
 