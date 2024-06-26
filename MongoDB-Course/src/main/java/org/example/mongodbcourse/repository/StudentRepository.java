package org.example.mongodbcourse.repository;

import org.example.mongodbcourse.model.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findStudentByEmail(String email);

    void deleteStudentByEmail(String email);

}
