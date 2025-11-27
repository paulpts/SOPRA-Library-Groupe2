import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AuteurDto } from '../dto/auteur-dto';

@Injectable({
  providedIn: 'root',
})
export class AuteurService {
  private apiUrl: string = '/auteur';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<AuteurDto[]> {
    return this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.http.get<AuteurDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next();
  }

  public findById(id: number): Observable<AuteurDto> {
    return this.http.get<AuteurDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(auteurDto: AuteurDto): void {
    const payload = auteurDto.toJson();

    if (!auteurDto.id) {
      this.http.post<AuteurDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<AuteurDto>(`${ this.apiUrl }/${ auteurDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: string): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
