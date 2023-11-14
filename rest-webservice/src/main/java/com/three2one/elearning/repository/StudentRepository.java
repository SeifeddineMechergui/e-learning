package com.three2one.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.three2one.elearning.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findByUsername(String username);
	  
}
