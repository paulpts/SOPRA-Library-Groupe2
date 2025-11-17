import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { LivreDto } from '../dto/livre-dto';

@Injectable({
  providedIn: 'root',
})
export class LivreService {
  
  private refresh$: Subject<void> = new Subject<void>();
    private apiUrl: string = "http://localhost:8080/api/livre";

  
  constructor(private http :HttpClient){}

  public findAll(): Observable<LivreDto[]> {
      return this.refresh$.pipe(
      startWith(null),
      switchMap(() => {
        return this.http.get<LivreDto[]>(this.apiUrl);
      })
    );
     
  }

    public refresh() {
    this.refresh$.next(); 
  }


  public findById(id:number):Observable<LivreDto>{
    return this.http.get<LivreDto>(`${ this.apiUrl }/${ id }`);
  }


  public save(livre:LivreDto){
    const payload = livre.toJson();

    if (!livre.id) {
      this.http.post<LivreDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    }

    else {
      this.http.put<LivreDto>(`${ this.apiUrl }/${ livre.id }`, payload).subscribe(() => this.refresh());
    }

  }

  public deleteById(id:number){

        this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());

  }



  
}
