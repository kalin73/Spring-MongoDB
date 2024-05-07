package org.example.mongodbcourse.repository;

import org.example.mongodbcourse.model.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
