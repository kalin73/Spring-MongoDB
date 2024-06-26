package org.example.mongodbcourse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mongodbcourse.model.entity.Address;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private Address address;

    private LocalDateTime createdAt;

    private List<String> favouriteSubjects;

    private BigDecimal totalSpentInBooks;
}
