import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { StudentService } from 'src/app/service/data/student.service';
import { CourseService } from 'src/app/service/data/course.service';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';

@Component({
  selector: 'app-course-view-edit',
  templateUrl: './course-view-edit.component.html',
  styleUrls: ['./course-view-edit.component.css']
})
export class CourseViewEditComponent implements OnInit {
  course={};
  studentId;
  courseId;
  componentMode;
  disabled : boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  
  constructor(private basicAuthenticationService: BasicAuthenticationService,private formBuilder: FormBuilder, private studentService: StudentService, private courseService: CourseService, private router: Router, private route:ActivatedRoute) { }


  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.courseId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      this.displayCourseDetails();

      if(this.componentMode == "editMode"){
          this.disabled = false;
      }else{
        this.disabled = true;
      }
    });
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  displayCourseDetails(){
    this.studentService.retreiveStudentCourse(this.basicAuthenticationService.getAuthenticatedUserId(), this.courseId).subscribe(
      response => {
        this.course = response as any;
      }
    )
  }
  onSave(){
    this.studentService.registerInCourse(this.basicAuthenticationService.getAuthenticatedUserId(),this.course).subscribe(
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
