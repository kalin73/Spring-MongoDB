package com.example.moviesbackend.model.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "confirmations")
@Data
public class Confirmation {
    private ObjectId id;
    private LocalDateTime created;
    private String token;
    private User user;

    public Confirmation(){

    }

    public Confirmation(User user){
        this.created = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
        this.user = user;
    }
}
