import { Injectable } from '@angular/core';
import { AuthRequestDto } from '../dto/auth-request-dto';
import { HttpClient } from '@angular/common/http';
import { AuthResponseDto } from '../dto/auth-response-dto';
import { SubscribeRequestDto } from '../dto/subscribe-request-dto';
import { SubscribeResponseDto } from '../dto/subscribe-response-dto';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  constructor(private http: HttpClient) { }

  public subscribe(request: SubscribeRequestDto): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.post<SubscribeResponseDto>('/utilisateur/inscription', request.toJson()).subscribe({
        // next => si la réponse est OK
        next: resp => {
          resolve();
        },

        // error => si la réponse est KO (30X, 40X, 50X)
        error: err => reject(err)
      });
    })
  }
}
