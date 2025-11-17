import { Injectable } from '@angular/core';
import { GenreDto } from '../dto/genre-dto';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class GenreService {
  private apiUrl: string = 'http://localhost:8080/api/genre';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<GenreDto[]> {
    return this.refresh$.pipe( // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      // Transformer le "void" que GenreDto[] en allant chercher les informations
      switchMap(() => {
        return this.http.get<GenreDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<GenreDto> {
    return this.http.get<GenreDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(genreDto: GenreDto): void {
    const payload = genreDto.toJson();

    if (!genreDto.id) {
      this.http.post<GenreDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    }

    else {
      this.http.put<GenreDto>(`${ this.apiUrl }/${ genreDto.id }`, payload).subscribe(() => this.refresh());
    }
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
