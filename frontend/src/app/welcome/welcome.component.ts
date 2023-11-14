import { ActivatedRoute } from '@angular/router';

import { Component, OnInit } from '@angular/core';//'./app.component';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

//public class SpringBootFirstWebApplication implements SomeInterface{
export class WelcomeComponent implements OnInit {

  message = 'Some Welcome Message'
  welcomeMessageFromService:string
  name = ''
  constructor(
    private route:ActivatedRoute, private basicAuthenticationService: BasicAuthenticationService) { 

  }

  // void init() {
  ngOnInit(){
    this.name = this.basicAuthenticationService.getAuthenticatedUser();
    
  }

  handleSuccessfulResponse(response){
    this.welcomeMessageFromService = response.message
  }

  handleErrorResponse(error) {
    this.welcomeMessageFromService = error.error.message
  }
}
