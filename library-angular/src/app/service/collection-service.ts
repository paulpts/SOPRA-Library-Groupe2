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

  public save(collectionDto: CollectionDto) {
    const payload = collectionDto.toJson();

    if (!collectionDto.id) {
      this.http.post<CollectionDto>(this.apiUrl, payload).subscribe(()=>this.refresh());
    }
else {
    this.http.put<CollectionDto>(`${this.apiUrl}/${collectionDto.id}`, payload).subscribe(()=>this.refresh());
  }
}

  public deleteById(id: number){
   this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }

}
