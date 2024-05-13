package org.example.mongodbcourse.web;

import lombok.RequiredArgsConstructor;
import org.example.mongodbcourse.model.dto.StudentDto;
import org.example.mongodbcourse.model.entity.Student;
import org.example.mongodbcourse.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents() {
        return this.studentService.getAllStudents();
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        return this.studentService.addStudent(studentDto);
    }

    @PatchMapping
    public Student updateStudent(@RequestBody StudentDto student) {
        return this.studentService.updateStudentInfo(student);
    }

    @DeleteMapping("/{email}")
    public String deleteStudent(@PathVariable(name = "email") String email) {
        return this.studentService.deleteStudent(email);
    }
}
