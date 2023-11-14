import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { HttpClient } from '@angular/common/http';
import { Course } from 'src/app/model/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllCourses() {
    return this.http.get<Course[]>(`${API_URL}/courses`);

  }

  deleteCourse(id){
    return this.http.delete(`${API_URL}/courses/${id}`);
  }

  retrieveCourse(id){
    return this.http.get<Course>(`${API_URL}/courses/${id}`);
  }

  updateCourse(id, course){
    return this.http.put(
          `${API_URL}/courses/${id}`
                , course);
  }

  createCourse(course){
    return this.http.post(
              `${API_URL}/courses`
                , course);
  }
}

