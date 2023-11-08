package com.example.moviesbackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    @Id
    private ObjectId id;

    private String body;

    @DocumentReference
    private User user;

    public Review(String body, User user) {
        this.body = body;
        this.user = user;
    }
}
