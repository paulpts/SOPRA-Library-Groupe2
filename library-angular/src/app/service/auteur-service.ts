import { Injectable } from '@angular/core';
import { AuteurDto } from '../dto/dto-auteur';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuteurService {

  private apiUrl: string = 'http://localhost:8080/api/auteur';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public refresh() {
  this.refresh$.next();
  }
  
  public findAll(): Observable<AuteurDto[]> {
    return this.refresh$.pipe(              // (c'est pour appliquer des opérateurs RxJS sur un Observable)
      startWith(null), // (permet d'emettre une premiere valeur tout de suite pour que le refresh marche)
      switchMap(() => 
        this.http.get<AuteurDto[]>(this.apiUrl))
      
    );
  }

  public save(auteur: AuteurDto): void {
    const payload = auteur.toJson();   // ca permet de transformer l'objet AuteurDTO en formatJSON à envoyer au back
    if(!auteur.id) {
      this.http.post<AuteurDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    } else {
      this.http.put<AuteurDto>(`${this.apiUrl}/${auteur.id}`, payload).subscribe(() => this.refresh());
    }

  }


  public findById(id:number): Observable<AuteurDto> {

    return this.http.get<AuteurDto>(`${this.apiUrl}/${id}`);  //this.http pour faire des requêtes HTTP

  }

  public deleteById(id:number): void {

    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());

  }
  
}
