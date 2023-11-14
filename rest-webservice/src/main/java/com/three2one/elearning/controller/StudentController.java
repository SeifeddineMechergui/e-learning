package com.three2one.elearning.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.three2one.elearning.entity.Course;
import com.three2one.elearning.entity.Student;
import com.three2one.elearning.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/api/students")
	public List<Student> findAll(Authentication authentication) {
		logger.info("Request-By: " + authentication.getName() + ", Action: RetreiveAllStudents ");
		return studentService.findAll();
	}

	@GetMapping("/api/students/{id}")
	public Student findById(@PathVariable long id, Authentication authentication) {
		Student student = studentService.findById(id);
		logger.info("Request-By: " + authentication.getName() + ", Action: findStudentById, ID : " + id
				+ ",  Retreived-Student : " + student.toString());
		return student;
	}

	@DeleteMapping("/api/students/{id}")
	public void deleteStudent(@PathVariable long id, Authentication authentication) {
		Student student = studentService.deleteById(id);
		logger.info("Request By : " + authentication.getName() + ", Action: deleteStudent, Deleted-Student : "
				+ student.toString());

	}

	@PostMapping("/api/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		Student savedStudent = studentService.save(student);

		logger.info("New Student Registration, Registered-Student = " + student.toString());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student,
			Authentication authentication) {
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		Student updatedStudent = studentService.updateStudent(id, student);
		
		logger.info("Request By : " + authentication.getName() + ", Action: updateStudent, Old-Student : "
				+ student.toString()+", Updated-Student : "+updatedStudent.toString());
		return new ResponseEntity<Student>(updatedStudent, HttpStatus.OK);
	}

	@PostMapping("/api/students/{id}/courses")
	public void registerInCourse(@PathVariable long id, @RequestBody Course course, Authentication authentication) {
		studentService.registerInCourse(id, course);
		logger.info("Request By : " + authentication.getName() + ", Action: registerInCourse, Registered-Course : "
				+ course.toString());
	}

	@DeleteMapping("/api/students/{studentId}/courses/{courseId}")
	public void unregisterFromCourse(@PathVariable long studentId, @PathVariable long courseId,
			Authentication authentication) {
		Course deletedCourse = studentService.unregisterFromCourse(studentId, courseId);
		logger.info("Request By : " + authentication.getName() + ", Action: unregisterFromCourse, Unregistered-Course : "
				+ deletedCourse.toString());
	}

	@GetMapping("/api/students/{id}/courses")
	public List<Course> retreiveStudentCourses(@PathVariable long id, Authentication authentication) {
		List<Course> courses = studentService.retrieveStudentCourses(id);
		logger.info("Request By : " + authentication.getName() + ", Action: retreiveStudentCourses, Student-Courses : "
				+ courses.toString());
		return courses;
	}
	@GetMapping("/api/students/{id}/courses/{courseId}")
	public Course retreiveStudentCourse(@PathVariable long id, @PathVariable long courseId, Authentication authentication) {
		Course course = studentService.retreiveStudentCourse(id,courseId);
		logger.info("Request By : " + authentication.getName() + ", Action: retreiveStudentCourse, Student-Course : "
				+ course.toString());
		return course;
	}
}
