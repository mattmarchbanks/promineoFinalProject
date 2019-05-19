package com.promineotech.classApi.request;

import java.util.Set;

public class AssignUsersRequest {
	
	private Long courseId;
	private Long instructorId;
	private Long studentId;
	private Set<Long> studentIds;
	private Set<Long> courseIds;
	
	public Long getInstructorId() {
		return instructorId;
	}
	
	public void setInstructorId(Long instructor) {
		this.instructorId = instructor;
	}
	
	public Set<Long> getStudentIds() {
		return studentIds;
	}
	
	public void setStudentIds(Set<Long> studentIds) {
		this.studentIds = studentIds;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Set<Long> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(Set<Long> courseIds) {
		this.courseIds = courseIds;
	}

}
