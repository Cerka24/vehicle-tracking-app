import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DashboardService} from "../services/dashboard.service";
import {CarStatisticsModel} from "../models/carStatistics";
import {Chart} from "chart.js/auto";
import {AvgKilometers} from "../models/avgKilometers";
import {AvgSpeed} from "../models/avgSpeed";
import {AvgTimeUsage} from "../models/avgTimeUsage";
import {AvgFuelConsumption} from "../models/avgFuelConsumption";
import {VehicleModel} from "../models/vehicle";
import {VehicleRegistrationService} from "../services/vehicle-registration.service";

@Component({
  selector: 'app-dashboard-statistics',
  templateUrl: './dashboard-statistics.component.html',
  styleUrls: ['./dashboard-statistics.component.css']
})
export class DashboardStatisticsComponent implements OnInit {

  public isLogged!: boolean;

  public carStatistics!: CarStatisticsModel;

  public chart: any;
  public avgKilometersList!: AvgKilometers[];
  public avgSpeedList!: AvgSpeed[];
  public avgTimeUsageList!: AvgTimeUsage[];
  public avgFuelConsumptionList!: AvgFuelConsumption[];

  public vehicle!: VehicleModel;

  constructor(private router: Router, private dashboardService: DashboardService, private activatedRoute: ActivatedRoute, private vehicleService: VehicleRegistrationService) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.activatedRoute.params.subscribe(
      params => {
        this.dashboardService.getTotal(this.activatedRoute.snapshot.queryParams['date']).subscribe(response => {
          this.carStatistics = response;
        })

        this.dashboardService.getAverageKilometers(this.activatedRoute.snapshot.queryParams['date']).subscribe(response => {
          this.avgKilometersList = response;
          this.chart = new Chart("MyChartAvgKilometers", {
            type: 'bar',

            data: {
              labels: this.avgKilometersList.map((avgKilometersModel) => avgKilometersModel.serialNoRpi),
              datasets: [
                { label: "Average kilometers chart",
                  data: this.avgKilometersList.map((avgKilometersModel) => avgKilometersModel.avgKilometers),
                },]
            },
            options: { aspectRatio:2.5}
          });
        })
        this.dashboardService.getAverageSpeed(this.activatedRoute.snapshot.queryParams['date']).subscribe(response => {
          this.avgSpeedList = response;
          this.chart = new Chart("MyChartAvgSpeed", {
            type: 'bar',

            data: {
              labels: this.avgSpeedList.map((avgSpeedModel) => avgSpeedModel.serialNoRpi),
              datasets: [
                { label: "Average speed chart",
                  data: this.avgSpeedList.map((avgSpeedModel) => avgSpeedModel.avgSpeed),
                },]
            },
            options: { aspectRatio:2.5}
          });
        })
        this.dashboardService.getAverageTimeUsage(this.activatedRoute.snapshot.queryParams['date']).subscribe(response => {
          this.avgTimeUsageList = response;
          this.chart = new Chart("MyChartAvgTimeUsage", {
            type: 'bar',

            data: {
              labels: this.avgTimeUsageList.map((avgTimeUsageModel) => avgTimeUsageModel.serialNoRpi),
              datasets: [
                { label: "Average time usage chart",
                  data: this.avgTimeUsageList.map((avgTimeUsageModel) => avgTimeUsageModel.avgTimeUsage),
                },]
            },
            options: { aspectRatio:2.5}
          });
        })
        this.dashboardService.getAverageFuelConsumption(this.activatedRoute.snapshot.queryParams['date']).subscribe(response => {
          this.avgFuelConsumptionList = response;
          this.chart = new Chart("MyChartAvgFuelConsumption", {
            type: 'bar',

            data: {
              labels: this.avgFuelConsumptionList.map((avgFuelConsumptionModel) => avgFuelConsumptionModel.serialNoRpi),
              datasets: [
                { label: "Average fuel consumption chart",
                  data: this.avgFuelConsumptionList.map((avgFuelConsumptionModel) => avgFuelConsumptionModel.avgFuelConsumption),
                },]
            },
            options: { aspectRatio:2.5}
          });
        })

        this.vehicleService.getVehicle().subscribe(response => {
          this.vehicle = response;
        })
      })
  }
}
