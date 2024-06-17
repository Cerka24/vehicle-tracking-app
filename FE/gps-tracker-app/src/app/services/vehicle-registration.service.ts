import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {DeviceModel} from "../models/device";
import {Observable} from "rxjs";
import {VehicleModel} from "../models/vehicle";

@Injectable({
  providedIn: 'root'
})
export class VehicleRegistrationService {

  private readonly baseUrl: string = `${environment.backendUrl}`;
  constructor(private http: HttpClient) { }

  addVehicle(vehicles: VehicleModel): Observable<DeviceModel> {
    let userId = localStorage.getItem('userId')
    let deviceId = localStorage.getItem('deviceId')
    vehicles.userId = Number(userId);
    vehicles.deviceId = Number(deviceId);
    return this.http.post<DeviceModel>(`${this.baseUrl}/vehicleRegistration`,vehicles);
  }

  getVehicle(): Observable<VehicleModel> {
    let deviceId = localStorage.getItem('deviceId')
    return this.http.get<VehicleModel>(`${this.baseUrl}/vehicleRegistration/${deviceId}`)
  }
}
