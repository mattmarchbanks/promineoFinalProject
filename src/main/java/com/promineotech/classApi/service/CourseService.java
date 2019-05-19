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
import com.promineotech.classApi.repository.InstructorRepository;
import com.promineotech.classApi.repository.StudentRepository;
import com.promineotech.classApi.request.AssignUsersRequest;
import com.promineotech.classApi.util.CourseStatus;

@Service
public class CourseService {
	
	private static final Logger logger = LogManager.getLogger(CourseService.class);
	
	@Autowired
	private CourseRepository repo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;
	
	public Course getCourseById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve course: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Course> getCourses() {
		return repo.findAll();
	}
	
	public Course startNewCourse(Set<Long> studentIds, Long instructorId, Long courseId) throws Exception {
		try {
			Course course = repo.findOne(courseId);
			course.setInstructor(instructorRepo.findOne(instructorId));
			course.setStudents(convertToStudentSet(studentRepo.findAll(studentIds)));
			//TODO create a couple methods to add this course to the instructor and add the course to each student
			return repo.save(course);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to begin new course with instructor: " + instructorId, e);
			throw e;
		}
	}
	
	public Course createNewCourse(Course course) {
		return repo.save(course);
	}
	
	public Course enrollUsers(AssignUsersRequest request) {
		Course course = repo.findOne(request.getCourseId());
		course.setInstructor(instructorRepo.findOne(request.getInstructorId()));
		course.setStudents(convertToStudentSet(studentRepo.findAll(request.getStudentIds())));
		return repo.save(course);
	}
	
	public Course updateCourse(Course course, Long id) throws Exception {
		try {
			Course oldCourse = repo.findOne(id);
			oldCourse.setTitle(course.getTitle());
			oldCourse.setStartDate(course.getStartDate());
			oldCourse.setEndDate(course.getEndDate());
			oldCourse.setStatus(course.getStatus());
			return repo.save(oldCourse);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update course: " + id, e);;
			throw new Exception ("Unable to update course.");
		}
	}
	
	public Course deleteCourse(Long courseId) throws Exception {
		try {
			Course course = repo.findOne(courseId);
			course.setStatus(CourseStatus.CANCELED);
			return course;
		} catch (Exception e) {
			logger.error("Exception occurred while trying to cancel course: " + courseId, e);
			throw new Exception("Unable to update course.");
		}
	}
	
	private Set<Student> convertToStudentSet(Iterable<Student> iterable) {
		Set<Student> set = new HashSet<Student>();
		for (Student student : iterable) {
			set.add(student);
		}
		return set;
	}

}
