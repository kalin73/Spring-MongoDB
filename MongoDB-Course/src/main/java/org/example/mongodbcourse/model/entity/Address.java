package org.example.mongodbcourse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseDocument {
    private String country;
    private String city;
    private String postCode;
}
