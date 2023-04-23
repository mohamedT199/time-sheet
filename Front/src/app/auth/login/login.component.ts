import { Component } from '@angular/core';
import {FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {AuthService} from "../auth.service";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(private authService: AuthService) {
  }
  email: any;
  password: any;
  loading: boolean = false;
  NotFound: any = false;

  myForm = new FormGroup({
    email: new FormControl(null,[
      Validators.required,
      Validators.email
    ]),
    password: new FormControl(null,[
      Validators.required,
      Validators.minLength(9)
    ])
  });

  load() {
    this.loading = true;

    setTimeout(() => {
      this.loading = false
    }, 2000);
  }

  submit() {
    this.authService.login(this.myForm.get("email")?.value+"", this.myForm.get("password")?.value+"");
    if(localStorage.getItem("token")) this.NotFound = true;
    else this.NotFound = false;
  }
}
