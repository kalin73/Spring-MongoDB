package org.example.mongodbcourse;

import org.example.mongodbcourse.model.entity.Address;
import org.example.mongodbcourse.model.entity.Student;
import org.example.mongodbcourse.model.enums.Gender;
import org.example.mongodbcourse.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoDbCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbCourseApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return a -> {
            String email = "pesho@abv.bg";
            Address address = new Address("Bulgaria", "Sofia", "1000");
            Student student = new Student("Pesho",
                    "Ivanov",
                    email,
                    Gender.MALE,
                    address,
                    LocalDateTime.now(),
                    List.of("Math", "Computer Science"),
                    BigDecimal.TEN
            );
            //usingMongoTemplate(repository, mongoTemplate, student, email);

            repository.findStudentByEmail(email).ifPresentOrElse(s -> System.out.println(s + " already exists!"),
                    () -> {
                        System.out.println("Inserting student " + student);
                        repository.save(student);
                    });
        };
    }

    private void usingMongoTemplate(StudentRepository repository, MongoTemplate mongoTemplate, Student student, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("Found many students with email: " + email);
        }

        if (students.isEmpty()) {
            System.out.println("Inserting student " + student);
            repository.save(student);

        } else System.out.println(student + " already exists!");
    }
}
