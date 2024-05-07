package org.example.mongodbcourse;

import org.example.mongodbcourse.model.entity.Address;
import org.example.mongodbcourse.model.entity.Student;
import org.example.mongodbcourse.model.enums.Gender;
import org.example.mongodbcourse.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoDbCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbCourseApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StudentRepository repository) {
        return a -> {
            Address address = new Address("Bulgaria", "Sofia", "1000");
            Student student = new Student("Pesho",
                    "Ivanov",
                    "pesho@abv.bg",
                    Gender.MALE,
                    address,
                    LocalDateTime.now(),
                    List.of("Math", "Computer Science"),
                    BigDecimal.TEN
            );
            repository.save(student);
        };
    }
}
