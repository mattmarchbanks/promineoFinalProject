package com.promineotech.classApi.view;

import java.sql.Date;
import java.util.Set;

import com.promineotech.classApi.entity.Course;
import com.promineotech.classApi.entity.Student;

public class StudentView {
	
	private Student student;
	
	public StudentView(Student student) {
		this.student = student;
	}
	
	public String getFirst_name() {
		return student.getFirst_name();
	}

	public String getLast_name() {
		return student.getLast_name();
	}

	public Date getEnroll_date() {
		return student.getEnroll_date();
	}

	public String getProgram() {
		return student.getProgram();
	}
	
	public Set<Course> getCourses() {
		return student.getCourses();
	}
	
}
