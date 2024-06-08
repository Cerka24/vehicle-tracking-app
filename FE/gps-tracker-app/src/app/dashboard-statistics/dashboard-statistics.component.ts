import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DashboardService} from "../services/dashboard.service";
import {CarStatisticsModel} from "../models/carStatistics";
import {Chart} from "chart.js/auto";
import {AvgKilometers} from "../models/avgKilometers";

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

  public carData!: CarStatisticsModel;

  public avgKilometers!: number
  public averageTimeUsage!: number
  public averageSpeed!: number
  public averageFuelConsumption!: number

  constructor(private router: Router, private dashboardService: DashboardService, private activatedRoute: ActivatedRoute) {
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
          this.chart = new Chart("MyChart", {
            type: 'bar',

            data: {
              labels: this.avgKilometersList.map((avgKilometersModel) => avgKilometersModel.serialNoRpi),
              datasets: [
                { label: "",
                  data: this.avgKilometersList.map((avgKilometersModel) => avgKilometersModel.avgKilometers),
                },]
            },
            options: { aspectRatio:2.5}
          });
        })
      })
  }

  cellClicked(cellValue: string) {
    console.log('Cell clicked:', cellValue);
    // Perform actions based on clicked cell value
  }
}
