package org.example.mongodbcourse.model.entity;

import lombok.Data;
import org.example.mongodbcourse.model.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "students")
@Data
public class Student extends BaseDocument {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Address address;
    private ZonedDateTime createdAt;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
}
