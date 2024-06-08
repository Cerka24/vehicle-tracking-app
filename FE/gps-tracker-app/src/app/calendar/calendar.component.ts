import {Component, OnInit} from '@angular/core';
import {CalendarOption} from "@fullcalendar/angular/private-types";
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import {ActivatedRoute, Router} from "@angular/router";
import {DashboardService} from "../services/dashboard.service";
import {CarStatisticsModel} from "../models/carStatistics";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-dashboard',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent {

  public isLogged!: boolean;

  constructor(private router: Router) {
  }

  calendarOptions: CalendarOption<any> = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin, interactionPlugin],
    dateClick: (date: any, id: number) => this.handleDateClick(date, id),
  }

  private handleDateClick(date: any, id: number) {
    console.log(date);
    this.router.navigate([
      '/dashboardStatistics',
    ], {queryParams: {date: date.dateStr}})
  }
}
