package com.promineotech.classApi.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.classApi.entity.Course;
import com.promineotech.classApi.entity.Instructor;
import com.promineotech.classApi.repository.CourseRepository;
import com.promineotech.classApi.repository.InstructorRepository;
import com.promineotech.classApi.request.AssignUsersRequest;

@Service
public class InstructorService {
	
	private static final Logger logger = LogManager.getLogger(InstructorService.class);
	
	@Autowired
	private InstructorRepository repo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	public Instructor getInstructorById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve instructor: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Instructor> getInstructors() {
		return repo.findAll();
	}
	
	public Instructor createInstructor(Instructor instructor) {
		return repo.save(instructor);
	}
	
	public Instructor updateInstructor(Instructor instructor, Long id) throws Exception {
		try {
			Instructor oldInstructor = repo.findOne(id);
			oldInstructor.setFirst_name(instructor.getFirst_name());
			oldInstructor.setLast_name(instructor.getLast_name());
			oldInstructor.setDept(instructor.getDept());
			oldInstructor.setHire_date(instructor.getHire_date());
			return repo.save(oldInstructor);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update instructor: " + id, e);
			throw new Exception("Unable to update instructor.");
		}
	}
	
	public void deleteInstructor(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete customer: " + id, e);
			throw new Exception("Unable to delete customer.");
		}
	}
	
	private Set<Course> convertToCourseSet(Iterable<Course> iterable) {
		Set<Course> set = new HashSet<Course>();
		for (Course course : iterable) {
			set.add(course);
		}
		return set;
	}
	
	public Instructor enrollUsers(AssignUsersRequest request) {
	Instructor instructor = repo.findOne(request.getInstructorId());
	instructor.setCourses(convertToCourseSet(courseRepo.findAll(request.getCourseIds())));
	return repo.save(instructor);
	}
}
