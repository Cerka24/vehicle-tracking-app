import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  public isLogged: boolean = false;
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
  }
  navigateToRegister() {
    this.router.navigate([
      'register'
    ]);
  }
  navigateToLogin() {
    this.router.navigate([
      'login'
    ])
  }
}
