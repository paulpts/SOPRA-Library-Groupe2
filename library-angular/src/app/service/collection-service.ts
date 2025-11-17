import { Injectable } from '@angular/core';
import { Subject, Observable, startWith, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CollectionDto } from '../dto/collection-dto';

@Injectable({
  providedIn: 'root',
})
export class CollectionService {
  private apiUrl: string = 'http://localhost:8080/api/collection';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}


  public findAll(): Observable<CollectionDto[]> {
    return this.refresh$.pipe(startWith(null), switchMap(() => {
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

  public save(collectionDto: CollectionDto): Observable<CollectionDto> {
    const payload = {...collectionDto};

    if (!collectionDto.id) {
      return this.http.post<CollectionDto>(this.apiUrl, payload);
    }
else {
    return this.http.put<CollectionDto>(`${this.apiUrl}/${collectionDto.id}`, payload);
  }
}

  public deleteById(id: number): Observable<void> {
   return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
