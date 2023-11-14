import { Component, OnInit } from '@angular/core';
import { Student } from '../model/student.model';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentService } from '../service/data/student.service';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  student : Student= new Student;
  errorMessage: boolean = false;
  message;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  constructor(private basicAuthenticationService: BasicAuthenticationService, private formBuilder: FormBuilder, private studentService: StudentService, private router: Router ) { }


  ngOnInit() {
    if(this.basicAuthenticationService.isUserLoggedIn()){
      this.router.navigate(['welcome'])
    }
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  onSave(){
    this.studentService.createStudent(this.student).subscribe(
      result => {
        this.login(this.student.username, this.student.password);
      },
      error => {
        console.log('oops', error);
        this.errorMessage = true;
        this.message = error.error.message
      }
    );
  }
  login(username, password) {
    this.basicAuthenticationService.executeAuthenticationService(username, password)
        .subscribe(
          data => {
            this.router.navigate(['welcome'])
          },
          error => {
            console.log(error)
          }
        )
  }
  close(){
    this.router.navigateByUrl("/login");
  }
}

