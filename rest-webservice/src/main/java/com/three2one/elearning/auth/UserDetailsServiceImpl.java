package com.three2one.elearning.auth;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.three2one.elearning.entity.Student;
import com.three2one.elearning.repository.StudentRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private StudentRepository studentRepository;
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		Student student = null;
		logger.info("loadUserByUsername username = " + username);
		student = studentRepository.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		if (student == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(student.getUsername(), student.getPassword(),
				grantedAuthorities);
	}
}
