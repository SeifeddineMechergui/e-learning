package com.three2one.elearning.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="seq_student")
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	@NotNull(message= "name must not be null")
	@Size(min=5, message="name should have atleast 5 characters")
	private String name;

	@Column(name = "username",unique=true)
	@NotNull(message= "username must not be null")
	@Size(min=4, message="username should have atleast 4 characters")
	private String username;
	
	@Column(name = "email")
	@Email(message= "invalid email format")
	private String email;
	
	@Column(name = "password",nullable=false)
	@Size(min=5, message="password should have atleast 5 characters")
	private String password;
	
	@Column(name = "birthdate")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthdate;
	
	@Column(name = "gender")
	private String gender;
	
	@OneToMany(mappedBy = "student",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Course> courses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		return "Student [name="+name+", username="+username+", email="+email+", gender="+gender+"]";
	}
	
}
