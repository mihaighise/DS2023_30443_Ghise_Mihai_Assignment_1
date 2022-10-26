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

  public deleteDevice(id: number | undefined) {
    return this.http.delete<any>(`${this.apiServerUrl}/devices/delete/` + id);
  }

  public getDevicesByUser(username: string | null): Observable<Device[]> {
    return this.http.get<Device[]>(`${this.apiServerUrl}/devices/byUser/` + username);
  }

  public addDevice(device: any): Observable<Device> {
    return this.http.post<Device>(`${this.apiServerUrl}/devices/add`, device);
  }

  public updateDevice(oldDeviceId: number, newDevice: Device): Observable<Device> {
    return this.http.put<Device>(`${this.apiServerUrl}/devices/update/` + oldDeviceId, newDevice);
  }
}
