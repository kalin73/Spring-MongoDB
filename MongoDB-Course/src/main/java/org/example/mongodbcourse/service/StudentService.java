package org.example.mongodbcourse.service;

import lombok.RequiredArgsConstructor;
import org.example.mongodbcourse.model.entity.Student;
import org.example.mongodbcourse.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
