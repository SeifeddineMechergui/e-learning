package com.three2one.elearning.service;

import java.util.List;

import com.three2one.elearning.entity.Course;
import com.three2one.elearning.entity.Student;

public interface StudentService {
	public List<Student> findAll();
	
	Student save(Student student);

	Student findById(long id);

	Student deleteById(long id);
	
	Student findByUserName(String username);

	Student updateStudent(long id, Student student);

	void registerInCourse(long id, Course course);
	
	Course unregisterFromCourse(long studentId,long courseId);
	Course retreiveStudentCourse(long studentId,long courseId);
	public List<Course> retrieveStudentCourses(long id);
}
