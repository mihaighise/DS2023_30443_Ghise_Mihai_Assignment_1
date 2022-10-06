import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Device } from './device';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiServerUrl}/devices`);
  }

  public getFreeDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiServerUrl}/devices/free`);
  }

  public deleteDevice(id: number) {
    return this.http.delete<any>(`${this.apiServerUrl}/devices/delete/` + id);
  }

  public getDevicesByUser(username: string): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiServerUrl}/devices/byUser/` + username);
  }
}
