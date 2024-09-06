import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Corso} from "../../interfaces/corso";
import {Observable} from "rxjs";

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
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`  // Replace with your actual token
    });
    let options = { headers: headers };
    return this.HttpClient.post<Corso>(`${this.baseUrl}`,data, options);
  }

  istruttoreCourses(token:string){
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`  // Replace with your actual token
    });
    return this.HttpClient.get<Corso[]>(`${this.baseUrl}/istruttore`, {headers})
  }

  deleteCourse(token:string, id:number){
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`  // Replace with your actual token
    });
    return this.HttpClient.delete<null> (`${this.baseUrl}/${id}`, {headers});
  }

  uploadFile(file: File): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.HttpClient.post(`${this.baseUrl}/upload`, formData, { responseType: 'text' });
  }


  submitForm(data: any): Observable<any> {
    return this.HttpClient.post(`${this.baseUrl}/submit`, data);
  }




}
