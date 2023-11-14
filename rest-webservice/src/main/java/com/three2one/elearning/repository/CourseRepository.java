package com.three2one.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.three2one.elearning.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
