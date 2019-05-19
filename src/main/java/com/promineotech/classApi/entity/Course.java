package com.promineotech.classApi.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.promineotech.classApi.entity.Instructor;
import com.promineotech.classApi.util.CourseStatus;

@Entity
public class Course {
	
	private Long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private Instructor instructor;
	private Set<Student> students;
	private CourseStatus status;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date start_date) {
		this.startDate = start_date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date end_date) {
		this.endDate = end_date;
	}
	
	@ManyToOne
	@JoinColumn(name = "instructorId")
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course", 
    	joinColumns = @JoinColumn(name = "studentId", referencedColumnName = "id"),
    	inverseJoinColumns = @JoinColumn(name = "courseId", referencedColumnName = "id"))
	public Set<Student> getStudents() {
		return students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public CourseStatus getStatus() {
		return status;
	}
	public void setStatus(CourseStatus status) {
		this.status = status;
	}

}
