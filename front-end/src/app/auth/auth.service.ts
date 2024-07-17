import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { inject } from '@angular/core';
import {catchError, tap} from 'rxjs/operators';
import {Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  httpClient = inject(HttpClient);
  baseUrl = 'http://localhost:8080/nessun_dorma'

  constructor() { }

  signup(data: any) {
    return this.httpClient.post(`${this.baseUrl}/auth/signup`, data);
  }

  login(data: any) {
    console.log("ciao1")
    const headers = new HttpHeaders(({
      'Content-type': 'application/json'
    }))
    return this.httpClient.post(`${this.baseUrl}/auth/login`, data, { headers })
      .pipe(tap((result) => {
        console.log("bbb")
        console.log(JSON.stringify(result));
        console.log(result);
        localStorage.setItem('authUser', JSON.stringify(result));
      }))
  }

  logout() {
    localStorage.removeItem('authUser');
  }

  isLoggedIn() {
    return localStorage.getItem('authUser') !== null;
  }

}
