package com.promineotech.classApi.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.classApi.entity.Course;
import com.promineotech.classApi.entity.Student;
import com.promineotech.classApi.repository.CourseRepository;
import com.promineotech.classApi.repository.StudentRepository;
import com.promineotech.classApi.request.AssignUsersRequest;

@Service
public class StudentService {
	
	private static final Logger logger = LogManager.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	public Student getStudentById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve customer: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Student> getStudents() {
		return repo.findAll();
	}
	
	public Student createStudent(Student student) {
		return repo.save(student);
	}
	
	public Student updateStudent(Student student, Long id) throws Exception {
		try {
			Student oldStudent = repo.findOne(id);
			oldStudent.setFirst_name(student.getFirst_name());
			oldStudent.setLast_name(student.getLast_name());
			oldStudent.setPassword(student.getPassword());
			oldStudent.setEnroll_date(student.getEnroll_date());
			oldStudent.setProgram(student.getProgram());
			return repo.save(oldStudent);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update student: " + id, e);
			throw new Exception("Unable to update student.");
		}		
	}
	
	public void deleteStudent(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete student: " + id, e);
			throw new Exception ("Unable to delete student.");
		}
	}
	
	private Set<Course> convertToCourseSet(Iterable<Course> iterable) {
		Set<Course> set = new HashSet<Course>();
		for (Course course : iterable) {
			set.add(course);
		}
		return set;
	}
	
	public Student enrollUsers(AssignUsersRequest request) {
		Student student = repo.findOne(request.getStudentId());
		student.setCourses(convertToCourseSet(courseRepo.findAll(request.getCourseIds())));
		return repo.save(student);
	}

}
