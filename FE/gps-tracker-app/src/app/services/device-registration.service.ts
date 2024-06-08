import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {DeviceModel} from "../models/device";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DeviceRegistrationService {

  private readonly baseUrl: string = `${environment.backendUrl}`;
  constructor(private http: HttpClient) { }

  deviceRegistration(devices: DeviceModel): Observable<DeviceModel> {
    let userId = localStorage.getItem('userId')
    devices.userId = Number(userId);
    return this.http.post<DeviceModel>(`${this.baseUrl}/deviceRegistration`,devices);
  }
}
