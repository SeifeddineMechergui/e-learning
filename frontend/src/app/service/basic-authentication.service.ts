import { API_URL } from './../app.constants';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { Student } from '../model/student.model';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'
export const AUTHENTICATED_USER_ID = 'authenticaterUserId'

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {
  private student :  Student;
  constructor(private http: HttpClient) { }

  executeAuthenticationService(username, password) {
    
    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({
        Authorization: basicAuthHeaderString
      })

    return this.http.get<Student>(
      `${API_URL}/authenticate`,
      {headers}).pipe(
        map(
          data => {
            this.student = data ;
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN, basicAuthHeaderString);
            sessionStorage.setItem(AUTHENTICATED_USER_ID, this.student.id.toString());

            return data;
          }
        )
      );
    //console.log("Execute Hello World Bean Service")
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }
  getAuthenticatedUserId() {
    return sessionStorage.getItem(AUTHENTICATED_USER_ID)
  }

  getAuthenticatedToken() {
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN)
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(AUTHENTICATED_USER_ID)
    sessionStorage.removeItem(TOKEN)
  }

}

export class AuthenticationBean{
  constructor(public message:string) { }
}