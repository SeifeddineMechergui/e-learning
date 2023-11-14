import { Component, OnInit } from '@angular/core';
import { StudentService } from '../service/data/student.service';
import { Router } from '@angular/router';
import { CourseService } from '../service/data/course.service';
import { Course } from '../model/course.model';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-list-courses',
  templateUrl: './list-courses.component.html',
  styleUrls: ['./list-courses.component.css']
})
export class ListCoursesComponent implements OnInit {
  courses:  Course[]
  message: string
  studentId;

  constructor(
    private basicAuthenticationService: BasicAuthenticationService,
    private courseService:CourseService,
    private router : Router,
    private studentService: StudentService
  ) { 

  }

  ngOnInit() {
    this.studentId= this.basicAuthenticationService.getAuthenticatedUserId();
    this.refreshCourses();
  }

  refreshCourses(){
    this.studentService.retreiveStudentCourses(this.studentId).subscribe(
      response => {
        this.courses = response as any;
      }
    )
  }
 
  onDelete(courseId) {
    // let deletedCourse : Course =  this.courses.find(x => x.id == course) [0];
    
        this.studentService.unregisterFromCourse(this.studentId,courseId).subscribe (
          response => {
            this.refreshCourses();
          },
          error => {
            console.log('oops', error);
          }
        )
  }


  onEdit(id) {
    this.router.navigate(['courses',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['course-data'])
  }
}

