import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { EditeurDto } from '../dto/editeur-dto';

@Injectable({
  providedIn: 'root',
})
export class EditeurService {
  private apiUrl: string = '/editeur';
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
      return;
    }

    this.http.put<EditeurDto>(`${ this.apiUrl }/${ editeurDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: string): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
