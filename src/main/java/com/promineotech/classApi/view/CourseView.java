package com.promineotech.classApi.view;

import java.sql.Date;
import java.util.Set;

import com.promineotech.classApi.entity.Course;
import com.promineotech.classApi.entity.Instructor;
import com.promineotech.classApi.entity.Student;
import com.promineotech.classApi.util.CourseStatus;

public class CourseView {
	
	private Course course;
	
	public CourseView(Course course) {
		this.course = course;
	}
	
	public String getTitle() {
		return course.getTitle();
	}
	
	public Date getStartDate() {
		return course.getStartDate();
	}
	
	public Date getEndDate() {
		return course.getEndDate();
	}
	
	public Instructor getInstructor() {
		return course.getInstructor();
	}
	
	public Set<Student> getStudents() {
		return course.getStudents();
	}
	
	public CourseStatus getStatus() {
		return course.getStatus();
	}

}
