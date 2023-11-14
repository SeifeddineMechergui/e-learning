package com.three2one.elearning.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="seq_course")
	@Column(name = "id")
	private long id;
	
	@Column(name = "name",nullable=false)
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "publish_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date publishDate;
	
	@Column(name = "last_updated")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date lastUpdated;
	
	@Column(name = "total_hours")
	private int totalHours;
	
	@Column(name = "instructor")
	private String instructor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "student_id")
	@JsonIgnore
    private Student student;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return String.format("Course [name=%s, description=%s, publishDate=%s, lastUpdated=%s, totalHours=%s]", name, description, publishDate.toString(), lastUpdated.toString(), String.valueOf(totalHours));
	}
}
