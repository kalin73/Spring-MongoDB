package org.example.mongodbcourse.service;

import lombok.RequiredArgsConstructor;
import org.example.mongodbcourse.model.dto.StudentDto;
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

    public String addStudent(StudentDto studentDto) {
        final String email = studentDto.getEmail();

        this.studentRepository.findStudentByEmail(email)
                .ifPresentOrElse(st -> {
                    throw new IllegalStateException("Student with email " + email + " already exists!");
                }, () -> {
                    final Student student = modelMapper.map(studentDto, Student.class);
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

    public Student updateStudentInfo(StudentDto studentDto) {
        //updateStudentWithMongoTemplate(studentDto);

        return this.studentRepository.save(updateStudent(studentDto));
    }

    private void updateStudentWithMongoTemplate(StudentDto studentDto) {
        mongoTemplate.update(Student.class)
                .matching(new Query().addCriteria(Criteria.where("email").is(studentDto.getEmail())))
                .apply(new Update().set("firstName", studentDto.getFirstName())
                        .set("lastName", studentDto.getLastName())
                        .set("gender", studentDto.getGender().toUpperCase())
                        .set("address", studentDto.getAddress())
                        .set("favouriteSubjects", studentDto.getFavouriteSubjects())
                        .set("totalSpentInBooks", studentDto.getTotalSpentInBooks()))
                .first();
    }

    private Student updateStudent(StudentDto studentDto) {
        final String email = studentDto.getEmail();
        final Student student = this.studentRepository.findStudentByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Student with email " + email + " already exists!"));

        student.setEmail(studentDto.getEmail() != null ? studentDto.getEmail() : student.getEmail());
        student.setFirstName(studentDto.getFirstName() != null ? studentDto.getFirstName() : student.getFirstName());
        student.setLastName(studentDto.getLastName() != null ? studentDto.getLastName() : student.getLastName());
        student.setAddress(studentDto.getAddress() != null ? studentDto.getAddress() : student.getAddress());
        student.setFavouriteSubjects(studentDto.getFavouriteSubjects() != null ? studentDto.getFavouriteSubjects()
                : student.getFavouriteSubjects());
        student.setTotalSpentInBooks(studentDto.getTotalSpentInBooks() != null ? studentDto.getTotalSpentInBooks()
                : student.getTotalSpentInBooks());

        return student;

    }
}
