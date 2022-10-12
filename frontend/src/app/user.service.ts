import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from './user';
import { Observable } from 'rxjs';
import { Device } from './device';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiServerUrl}/users/getAll`);
  }

  public deleteUser(username: string) {
    return this.http.delete<any>(`${this.apiServerUrl}/users/delete/` + username);
  }

  public assignDevicesToUser(id: number | undefined, devices: Device[]) {
    return this.http.put<any>(`${this.apiServerUrl}/users/assignDevices/` + id, devices);
  }

  public addUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/users/add`, user);
  }
}
