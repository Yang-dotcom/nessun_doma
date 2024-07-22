import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Prenotazione} from "../../interfaces/prenotazione";

@Injectable({
  providedIn: 'root'
})
export class PrenotazioniServiceService {
  HttpClient = inject(HttpClient);
  baseUrl = 'http://localhost:8080/nessun_dorma/prenotazioni'

  constructor() { }

  //TODO limit to only the logged in user
  availablePrenotazioni(token: string) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`  // Replace with your actual token
    });
    return this.HttpClient.get<Prenotazione[]> (`${this.baseUrl}/utente`, {headers});
  }

  createPrenotazione(body:any, token:string){
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`  // Replace with your actual token
    });
    let options = { headers: headers };
   return this.HttpClient.post<Prenotazione>(`${this.baseUrl}`, body, options)
  }
}
