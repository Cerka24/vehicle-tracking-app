import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {CarStatisticsModel} from "../models/carStatistics";
import {AvgKilometers} from "../models/avgKilometers";
import {AvgSpeed} from "../models/avgSpeed";
import {AvgTimeUsage} from "../models/avgTimeUsage";
import {AvgFuelConsumption} from "../models/avgFuelConsumption";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private readonly baseUrl: string = `${environment.backendUrl}`;

  constructor(private http: HttpClient) { }

  getTotal(date: string): Observable<CarStatisticsModel> {
    console.log(date)

    let params = new HttpParams()
      .set('date', date);
    let userId = localStorage.getItem('userId')

    return this.http.get<CarStatisticsModel>(`${this.baseUrl}/statistics/total/${userId}`, {params})
  }

  getAverageKilometers(date: string): Observable<AvgKilometers[]> {
    console.log(date)

    let params = new HttpParams()
      .set('date', date);
    let userId = localStorage.getItem('userId')

    return this.http.get<AvgKilometers[]>(`${this.baseUrl}/statistics/avgKilometers/${userId}`, {params})
  }

  getAverageSpeed(date: string): Observable<AvgSpeed[]> {
    console.log(date)

    let params = new HttpParams()
      .set('date', date);
    let userId = localStorage.getItem('userId')

    return this.http.get<AvgSpeed[]>(`${this.baseUrl}/statistics/avgSpeed/${userId}`, {params})
  }

  getAverageTimeUsage(date: string): Observable<AvgTimeUsage[]> {
    console.log(date)

    let params = new HttpParams()
      .set('date', date);
    let userId = localStorage.getItem('userId')

    return this.http.get<AvgTimeUsage[]>(`${this.baseUrl}/statistics/avgTimeUsage/${userId}`, {params})
  }

  getAverageFuelConsumption(date: string): Observable<AvgFuelConsumption[]> {
    console.log(date)

    let params = new HttpParams()
      .set('date', date);
    let userId = localStorage.getItem('userId')

    return this.http.get<AvgFuelConsumption[]>(`${this.baseUrl}/statistics/avgFuelConsumption/${userId}`, {params})
  }
}
