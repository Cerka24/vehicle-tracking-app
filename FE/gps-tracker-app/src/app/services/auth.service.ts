import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {UserModel} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseUrl: string = `${environment.backendUrl}`;
  constructor(private http: HttpClient) { }
  login(userData: UserModel): Observable<UserModel>{
    return this.http.post<UserModel>(`${this.baseUrl}/logIn`, userData)
  }

  register(userData: UserModel): Observable<UserModel>{
    return this.http.post<UserModel>(`${this.baseUrl}/register`, userData)
  }
}
