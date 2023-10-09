package com.softuni.mongodbexample.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softuni.mongodbexample.entity.StudentEntity;
import com.softuni.mongodbexample.service.StudentService;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<StudentEntity> fetchAllStudents() {
		return studentService.getAllStudents();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addStudent(@RequestBody StudentEntity student) {
		this.studentService.addStudent(student);
	}
}
