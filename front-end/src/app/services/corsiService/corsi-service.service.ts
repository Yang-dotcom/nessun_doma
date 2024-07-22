import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Corso} from "../../interfaces/corso";

@Injectable({
  providedIn: 'root'
})
export class CorsiServiceService {
  HttpClient = inject(HttpClient);
  baseUrl = 'http://localhost:8080/nessun_dorma/corsi'

  constructor() { }


  availableCourses(){
    return this.HttpClient.get<Corso[]>(`${this.baseUrl}/available`);
  }

  createCourse(data: any, token:string){
    let params = new HttpParams()
                            .set('body', data)
                            .set('token', token);
    return this.HttpClient.post(`${this.baseUrl}`, params);
  }



}
