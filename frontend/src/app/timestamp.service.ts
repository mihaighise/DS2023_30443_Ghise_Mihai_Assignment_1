import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Timestamp } from './timestamp';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TimestampService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTimestampsByUser(username: string | null, deviceId: number | undefined): Observable<Timestamp[]> {
    return this.http.get<Timestamp[]>(`${this.apiServerUrl}/timestamps/byUser/` + username + '/' + deviceId)
  }
}
