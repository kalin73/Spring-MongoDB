package org.example.mongodbcourse.service;

import lombok.RequiredArgsConstructor;
import org.example.mongodbcourse.model.dto.StudentUpdateDto;
import org.example.mongodbcourse.model.entity.Student;
import org.example.mongodbcourse.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String deleteStudent(String email) {
        long count = this.studentRepository.count();

        studentRepository.deleteStudentByEmail(email);

        return count > this.studentRepository.count() ? "Deleted" : "Not found";
    }

    public Student updateStudent(StudentUpdateDto studentUpdateDto) {
        Student student = this.modelMapper.map(studentUpdateDto, Student.class);

        return null;
    }
}
