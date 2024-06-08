import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DeviceRegistrationService} from "../services/device-registration.service";

@Component({
  selector: 'app-device-registration',
  templateUrl: './device-registration.component.html',
  styleUrls: ['./device-registration.component.css']
})
export class DeviceRegistrationComponent implements OnInit {

  public deviceRegisterForm!: FormGroup

  constructor(private router: Router, private formBuilder: FormBuilder, private deviceRegistration: DeviceRegistrationService, private _snackBar: MatSnackBar, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.deviceRegisterForm = this.formBuilder.group({
      'maxConsumption': ['', [Validators.required]],
      'minConsumption': ['', [Validators.required]],
      'avgConsumption': ['', [Validators.required]],
      'serialNoRpi': ['', [Validators.required]],
    });
  }

  public submit(): void {
    if (!this.deviceRegisterForm.valid) {
      this._snackBar.open("Input is not valid", '', {
        duration: 1000
      })
      return;
    }

    this.deviceRegistration.deviceRegistration(this.deviceRegisterForm.value).subscribe((data:any) => {
      console.log(this.deviceRegisterForm.value);
      console.log(JSON.stringify(data));

      this.router.navigate(["/calendar"]);

    }, error => {
      this._snackBar.open("Unable to register car details", '', {
        duration: 1000
      })
    })
  }
}
