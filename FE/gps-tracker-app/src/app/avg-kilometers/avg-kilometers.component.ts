import {Component, OnInit} from '@angular/core';
import {Chart} from "chart.js/auto";
import {ActivatedRoute, Router} from "@angular/router";
import {DashboardService} from "../services/dashboard.service";
import {AvgKilometers} from "../models/avgKilometers";

@Component({
  selector: 'app-avg-kilometers',
  templateUrl: './avg-kilometers.component.html',
  styleUrls: ['./avg-kilometers.component.css']
})
export class AvgKilometersComponent implements OnInit {

  public chart: any;
  public avgKilometersList!: AvgKilometers[];

  constructor(private router: Router, private dashboardService: DashboardService, private activatedRoute: ActivatedRoute) {
  }
  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.activatedRoute.params.subscribe(
      params => {
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
}
