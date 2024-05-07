package org.example.mongodbcourse.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Data
public class Address extends BaseDocument {
    private String country;
    private String city;
    private String postCode;
}
