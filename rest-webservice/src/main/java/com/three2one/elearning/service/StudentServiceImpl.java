package com.three2one.elearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three2one.elearning.entity.Course;
import com.three2one.elearning.entity.Student;
import com.three2one.elearning.exception.CourseNotFoundException;
import com.three2one.elearning.exception.StudentNotFoundException;
import com.three2one.elearning.exception.UsernameAlreadyExistsException;
import com.three2one.elearning.repository.CourseRepository;
import com.three2one.elearning.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;
	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student save(Student student) {
		if (studentRepository.findByUsername(student.getUsername()) != null) {
			throw new UsernameAlreadyExistsException("username already exists");
		}
		return studentRepository.save(student);
	}

	@Override
	public Student findById(long id) {
		Optional<Student> student = studentRepository.findById(id);

		if (student.isPresent()) {
			return student.get();
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public Student deleteById(long id) {
		Optional<Student> student = studentRepository.findById(id);

		if (student.isPresent()) {
			studentRepository.delete(student.get());
			return student.get();
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public Student updateStudent(long id, Student student) {
		Optional<Student> existingStudent = studentRepository.findById(id);

		if (existingStudent.isPresent()) {
			Student updatedStudent = studentRepository.save(student);
			return updatedStudent;
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public void registerInCourse(long id, Course course) {

		Optional<Student> studentOptional = studentRepository.findById(id);
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();
			course.setStudent(student);
			courseRepository.save(course);
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public Course unregisterFromCourse(long studentId, long courseId) {
		
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (studentOptional.isPresent()) {
			Optional<Course> courseOptional = courseRepository.findById(courseId);
			if (courseOptional.isPresent()) {
				courseRepository.delete(courseOptional.get());
				return courseOptional.get();
			}else {
				throw new CourseNotFoundException("course not found");
			}
			
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public List<Course> retrieveStudentCourses(long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			System.out.println("Getting Courses");
			return student.get().getCourses();
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public Student findByUserName(String username) {
		Student student = studentRepository.findByUsername(username);

		if (student != null) {
			return student;
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public Course retreiveStudentCourse(long studentId, long courseId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (courseOptional.isPresent()) {
			return courseOptional.get();
		}else {
			throw new CourseNotFoundException("course not found");
		}
	}

}
