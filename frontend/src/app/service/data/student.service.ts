import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { HttpClient } from '@angular/common/http';
import { Student } from 'src/app/model/student.model';
import { Course } from 'src/app/model/course.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllStudents() {
    return this.http.get<Student[]>(`${API_URL}/students`);

  }

  deleteStudent(id){
    return this.http.delete(`${API_URL}/students/${id}`);
  }

  retrieveStudent(id){
    return this.http.get<Student>(`${API_URL}/students/${id}`);
  }

  updateStudent(id, student){
    return this.http.put(
          `${API_URL}/students/${id}`
                , student);
  }

  createStudent(student){
    return this.http.post(
              `${API_URL}/students`
                , student);
  }
  registerInCourse(id, course){
    return this.http.post(
              `${API_URL}/students/${id}/courses`
                , course);
  }
  unregisterFromCourse(studentId, courseId){
    return this.http.delete(`${API_URL}/students/${studentId}/courses/${courseId}`);
  }
  retreiveStudentCourses(id){
    return this.http.get<Course>(`${API_URL}/students/${id}/courses`);
  }
  retreiveStudentCourse(studentId, courseId){
    return this.http.get<Course>(`${API_URL}/students/${studentId}/courses/${courseId}`);
  }
}
