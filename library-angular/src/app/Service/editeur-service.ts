import { Injectable } from '@angular/core';
import { EditeurDto } from '../dto/editeur-dto';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EditeurService {
  private apiUrl: string = 'http://localhost:8080/api/editeur';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<EditeurDto[]> {
    return this.refresh$.pipe(
      
      startWith(null),

      
      switchMap(() => {
        return this.http.get<EditeurDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); 
  }

  public findById(id: number): Observable<EditeurDto> {
    return this.http.get<EditeurDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(editeurDto: EditeurDto): void {
    const payload = editeurDto.toJson();

    if (!editeurDto.id) {
      this.http.post<EditeurDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    }
    else {
      this.http.put<EditeurDto>(`${ this.apiUrl }/${ editeurDto.id }`, payload).subscribe(() => this.refresh());
    }
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}