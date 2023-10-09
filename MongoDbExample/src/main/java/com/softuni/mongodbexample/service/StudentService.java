package com.softuni.mongodbexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softuni.mongodbexample.entity.StudentEntity;
import com.softuni.mongodbexample.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;
	

	public List<StudentEntity> getAllStudents() {
		return this.studentRepository.findAll();
	}


	public void addStudent(StudentEntity student) {
		this.studentRepository.save(student);
		
	}

}
