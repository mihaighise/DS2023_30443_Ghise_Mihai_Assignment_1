import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiServerUrl = environment.apiBaseUrl;
  
  loggedUser!: User;

  constructor(private http: HttpClient) { }

  logIn(username: string, password: string): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/users/login` + '/' + username + '/' + password);
  }

  setLoggedUser(user: User) {
    this.loggedUser = user;
    localStorage.setItem('userRole', user.userRole);
    localStorage.setItem('username', user.username);
  }

  getLoggedUser(): string | null{
    return localStorage.getItem('username');
  }
}
