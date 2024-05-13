package org.example.mongodbcourse.service;

import lombok.RequiredArgsConstructor;
import org.example.mongodbcourse.model.dto.StudentUpdateDto;
import org.example.mongodbcourse.model.entity.Student;
import org.example.mongodbcourse.model.enums.Gender;
import org.example.mongodbcourse.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final MongoTemplate mongoTemplate;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String addStudent(StudentUpdateDto studentUpdateDto) {
        final String email = studentUpdateDto.getEmail();

        this.studentRepository.findStudentByEmail(email)
                .ifPresentOrElse(st -> {
                    throw new IllegalStateException("Student with email " + email + " already exists!");
                }, () -> {
                    final Student student = modelMapper.map(studentUpdateDto, Student.class);
                    student.setCreatedAt(LocalDateTime.now());

                    this.studentRepository.save(student);
                });

        return "Added";
    }

    public String deleteStudent(String email) {
        long count = this.studentRepository.count();

        studentRepository.deleteStudentByEmail(email);

        return count > this.studentRepository.count() ? "Deleted" : "Not found";
    }

    public Student updateStudentInfo(StudentUpdateDto studentUpdateDto) {
        mongoTemplate.update(Student.class)
                .matching(new Query().addCriteria(Criteria.where("email").is(studentUpdateDto.getEmail())))
                .apply(new Update().set("firstName", studentUpdateDto.getFirstName())
                        .set("lastName", studentUpdateDto.getLastName())
                        .set("gender", studentUpdateDto.getGender().toUpperCase())
                        .set("address", studentUpdateDto.getAddress())
                        .set("favouriteSubjects", studentUpdateDto.getFavouriteSubjects())
                        .set("totalSpentInBooks", studentUpdateDto.getTotalSpentInBooks()))
                .first();

        return this.studentRepository.findStudentByEmail(studentUpdateDto.getEmail()).get();
    }

    private Student updateStudent(StudentUpdateDto studentUpdateDto, Student student) {
        student.setEmail(studentUpdateDto.getEmail());
        student.setFirstName(studentUpdateDto.getFirstName());
        student.setLastName(studentUpdateDto.getLastName());
        student.setAddress(studentUpdateDto.getAddress());
        student.setGender(Gender.valueOf(studentUpdateDto.getGender().toUpperCase()));
        student.setFavouriteSubjects(studentUpdateDto.getFavouriteSubjects());
        student.setTotalSpentInBooks(studentUpdateDto.getTotalSpentInBooks());

        return student;

    }
}
