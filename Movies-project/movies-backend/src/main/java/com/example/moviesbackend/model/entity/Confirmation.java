package com.example.moviesbackend.model.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "confirmations")
@Data
public class Confirmation {
    private ObjectId id;
    private LocalDateTime created;
    private String token;
}
