package com.softuni.mongodbexample.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softuni.mongodbexample.entity.StudentEntity;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {
	Optional<StudentEntity> findStudentByEmail(String email);
}
