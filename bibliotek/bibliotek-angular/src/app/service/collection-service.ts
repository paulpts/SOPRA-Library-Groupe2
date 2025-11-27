import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { CollectionDto } from '../dto/collection-dto';

@Injectable({
  providedIn: 'root',
})
export class CollectionService {
  private apiUrl: string = '/collection';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<CollectionDto[]> {
    return this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.http.get<CollectionDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next();
  }

  public findById(id: number): Observable<CollectionDto> {
    return this.http.get<CollectionDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(collectionDto: CollectionDto): void {
    const payload = collectionDto.toJson();

    if (!collectionDto.id) {
      this.http.post<CollectionDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<CollectionDto>(`${ this.apiUrl }/${ collectionDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: string): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
