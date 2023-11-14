import { HardcodedAuthenticationService } from './../service/hardcoded-authentication.service';
import { Component, OnInit } from '@angular/core';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  //isUserLoggedIn: boolean = false;

  constructor(private basicAuthenticationService 
    : BasicAuthenticationService) { }

  ngOnInit() {
    //this.isUserLoggedIn = this.hardcodedAuthenticationService.isUserLoggedIn();
  }

}
