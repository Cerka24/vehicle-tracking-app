import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LandingComponent} from "./landing/landing.component";
import {LogInComponent} from "./log-in/log-in.component";
import {CalendarComponent} from "./calendar/calendar.component";
import {DeviceRegistrationComponent} from "./device-registration/device-registration.component";
import {DashboardStatisticsComponent} from "./dashboard-statistics/dashboard-statistics.component";
import {VehicleRegistrationComponent} from "./vehicle-registration/vehicle-registration.component";

const routes: Routes = [
  {
    path: "",
    component: LandingComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "login",
    component: LogInComponent
  },
  {
    path: "deviceRegistration",
    component: DeviceRegistrationComponent
  },
  {
    path: "vehicleRegistration",
    component: VehicleRegistrationComponent
  },
  {
    path: "calendar",
    component: CalendarComponent
  },
  {
    path: "dashboardStatistics",
    component: DashboardStatisticsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
