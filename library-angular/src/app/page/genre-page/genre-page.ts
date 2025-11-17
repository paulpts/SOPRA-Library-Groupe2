import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { GenreDto } from '../../dto/genre-dto';
import { GenreService } from '../../service/genre-service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-genre-page',
  imports: [ CommonModule, RouterLink, ReactiveFormsModule ],
  templateUrl: './genre-page.html',
  styleUrls: ['./genre-page.css'],
})
export class GenrePage implements OnInit {
  protected genres$!: Observable<GenreDto[]>;
  protected genreForm!: FormGroup;
  protected labelCtrl!: FormControl;
  protected editingGenre!: GenreDto | null;

  constructor(private genreService: GenreService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.genres$ = this.genreService.findAll();

    this.labelCtrl = this.formBuilder.control('', Validators.required);

    this.genreForm = this.formBuilder.group({
      label: this.labelCtrl
    });
  }

  public trackGenre(index: number, value: GenreDto) {
    return value.id;
  }

  public creerOuModifier() {
    if (this.editingGenre) {
      this.genreService.save(new GenreDto(this.editingGenre.id, this.labelCtrl.value));
    }

    else {
      this.genreService.save(new GenreDto(0, this.labelCtrl.value));
    }

    this.editingGenre = null;
    this.labelCtrl.setValue("");
  }

  public editer(genre: GenreDto) {
    this.editingGenre = genre;
    this.labelCtrl.setValue(genre.libelle);
  }

  public supprimer(genre: GenreDto) {
    this.genreService.deleteById(genre.id);
  }
}
