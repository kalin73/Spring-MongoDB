package org.example.mongodbcourse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mongodbcourse.model.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseDocument {
    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private Gender gender;

    private Address address;

    private LocalDateTime createdAt;

    private List<String> favouriteSubjects;

    private BigDecimal totalSpentInBooks;
}
