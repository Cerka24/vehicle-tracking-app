import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  public logInForm!: FormGroup
  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.logInForm = this.formBuilder.group({
      'userName': ['', [Validators.required]],
      'password': ['', [Validators.required, Validators.min(6), Validators.max(16)]],
    });
  }

  public submit(): void {
    if (!this.logInForm.valid) {
      this._snackBar.open("Input is not valid", '', {
        duration: 1000
      })
      return;
    }

    this.authService.login(this.logInForm.value).subscribe((data:any) => {
      console.log(this.logInForm.value);
      console.log(JSON.stringify(data));

      localStorage.setItem('userId', data.id)

      this.router.navigate(["deviceRegistration"]);

    }, error => {
      this._snackBar.open("Unable to log in account", '', {
        duration: 1000
      })
    })
  }
}
