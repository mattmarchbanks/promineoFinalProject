package com.promineotech.classApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.classApi.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
