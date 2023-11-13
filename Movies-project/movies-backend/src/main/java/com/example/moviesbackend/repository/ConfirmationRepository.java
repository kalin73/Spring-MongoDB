package com.example.moviesbackend.repository;

import com.example.moviesbackend.model.entity.Confirmation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationRepository extends MongoRepository<Confirmation, ObjectId> {
}
