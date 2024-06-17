import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { RegisterComponent } from './register/register.component';
import { LogInComponent } from './log-in/log-in.component';
import { CalendarComponent } from './calendar/calendar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { DeviceRegistrationComponent } from './device-registration/device-registration.component';
import {FullCalendarModule} from "@fullcalendar/angular";
import { DashboardStatisticsComponent } from './dashboard-statistics/dashboard-statistics.component';
import {MatTableModule} from "@angular/material/table";
import { AvgKilometersComponent } from './avg-kilometers/avg-kilometers.component';
import { OpenStreetMapComponent } from './open-street-map/open-street-map.component';
import { VehicleRegistrationComponent } from './vehicle-registration/vehicle-registration.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    RegisterComponent,
    LogInComponent,
    CalendarComponent,
    DeviceRegistrationComponent,
    DashboardStatisticsComponent,
    AvgKilometersComponent,
    OpenStreetMapComponent,
    VehicleRegistrationComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        ReactiveFormsModule,
        MatInputModule,
        MatButtonModule,
        HttpClientModule,
        MatSnackBarModule,
        FullCalendarModule,
        MatTableModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
