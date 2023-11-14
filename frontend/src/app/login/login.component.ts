import { BasicAuthenticationService } from './../service/basic-authentication.service';
import { HardcodedAuthenticationService } from './../service/hardcoded-authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  errorMessage = 'Invalid Credentials'
  invalidLogin = false

  constructor(private router: Router,
    private basicAuthenticationService: BasicAuthenticationService) { }

  ngOnInit() {
    if(this.basicAuthenticationService.isUserLoggedIn()){
      this.router.navigate(['welcome'])
    }
  }


  handleBasicAuthLogin() {
    this.basicAuthenticationService.executeAuthenticationService(this.username, this.password)
        .subscribe(
          data => {
            this.router.navigate(['welcome'])
            this.invalidLogin = false      
          },
          error => {
            console.log(error)
            this.invalidLogin = true
          }
        )
  }
}
