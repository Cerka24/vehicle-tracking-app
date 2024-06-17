import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {VehicleRegistrationService} from "../services/vehicle-registration.service";

@Component({
  selector: 'app-vehicle-registration',
  templateUrl: './vehicle-registration.component.html',
  styleUrls: ['./vehicle-registration.component.css']
})
export class VehicleRegistrationComponent {
  public vehicleRegistrationForm!: FormGroup

  constructor(private router: Router, private formBuilder: FormBuilder, private vehicleRegistrationService: VehicleRegistrationService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.vehicleRegistrationForm = this.formBuilder.group({
      'vehicleBrand': ['', [Validators.required]],
      'vehicleName': ['', [Validators.required]],
      'vehicleType': ['', [Validators.required]],
      'yearOfProduction': ['', [Validators.required]],
    });
  }

  public submit(): void {
    if (!this.vehicleRegistrationForm.valid) {
      this._snackBar.open("Input is not valid", '', {
        duration: 1000
      })
      return;
    }

    this.vehicleRegistrationService.addVehicle(this.vehicleRegistrationForm.value).subscribe((data:any) => {
      console.log(this.vehicleRegistrationForm.value);
      console.log(JSON.stringify(data));

      this.router.navigate(["/calendar"]);

    }, error => {
      this._snackBar.open("Unable to register car details", '', {
        duration: 1000
      })
    })
  }
}
