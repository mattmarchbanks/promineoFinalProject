package com.promineotech.classApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.classApi.entity.Instructor;
import com.promineotech.classApi.service.InstructorService;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
	
	@Autowired
	private InstructorService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getInstructor(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getInstructorById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getInstructors() {
		return new ResponseEntity<Object>(service.getInstructors(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createInstructor(@RequestBody Instructor instructor) {
		return new ResponseEntity<Object>(service.createInstructor(instructor), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateInstructor(@RequestBody Instructor instructor, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateInstructor(instructor, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteInstructor(@PathVariable Long id) {
		try {
			service.deleteInstructor(id);
			return new ResponseEntity<Object>("Successfully deleted instructor with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
