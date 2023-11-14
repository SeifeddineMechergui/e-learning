package com.three2one.elearning.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.three2one.elearning.entity.Student;
import com.three2one.elearning.repository.StudentRepository;

//Controller
@CrossOrigin(origins="*")
@RestController
public class AuthenticationController {
	@Autowired
	private StudentRepository studentRepository;
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@GetMapping(path = "/api/authenticate")
	public Student authenticate(Authentication authentication) {
		logger.info("Request-By: " + authentication.getName() + ", Action: authenticate ");
		Student student = studentRepository.findByUsername(authentication.getName());
		return student;
	}

}
