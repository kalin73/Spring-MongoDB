package com.softuni.mongodbexample;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.softuni.mongodbexample.entity.Address;
import com.softuni.mongodbexample.entity.Gender;
import com.softuni.mongodbexample.entity.StudentEntity;
import com.softuni.mongodbexample.repository.StudentRepository;

@SpringBootApplication
public class MongoDbExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("Bulgaria", "Vratza", "3000");
			Address address2 = new Address("Bulgaria", "Montana", "3400");

			StudentEntity student = new StudentEntity("Kalin", "Kostadinov", "kalin@abv.bg", Gender.MALE, address,
					List.of("Computer Science"), BigDecimal.TEN, LocalDateTime.now());

			StudentEntity student2 = new StudentEntity("Strahil", "Dimitrov", "straho1@abv.bg", Gender.MALE, address2,
					List.of("Computer Science"), BigDecimal.TEN, LocalDateTime.now());

			studentRepository.findStudentByEmail("kalin@abv.bg").ifPresentOrElse(s -> {
				System.out.println("The user is present!");
			}, () -> {
				studentRepository.save(student);
			});

			usingMongoTemplateAndQuery(studentRepository, mongoTemplate, student);

		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate,
			StudentEntity student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is("kalin@abv.bg"));

		List<StudentEntity> students = mongoTemplate.find(query, StudentEntity.class);

		if (students.isEmpty()) {
			studentRepository.save(student);
		}
	}
}
