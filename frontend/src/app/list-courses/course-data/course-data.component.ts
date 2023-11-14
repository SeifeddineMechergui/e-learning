import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { StudentService } from 'src/app/service/data/student.service';
import { CourseService } from 'src/app/service/data/course.service';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';

@Component({
  selector: 'app-course-data',
  templateUrl: './course-data.component.html',
  styleUrls: ['./course-data.component.css']
})
export class CourseDataComponent implements OnInit {
  course={};
  studentId;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  
  constructor(private basicAuthenticationService: BasicAuthenticationService, private formBuilder: FormBuilder, private studentService: StudentService, private courseService: CourseService, private router: Router, private route:ActivatedRoute) { }


  ngOnInit() {
      this.studentId= this.basicAuthenticationService.getAuthenticatedUserId();
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
    this.studentService.registerInCourse(this.studentId,this.course).subscribe(
      result => {
        this.router.navigateByUrl("/courses");
      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close(){
    this.router.navigateByUrl("/courses");
  }
}
