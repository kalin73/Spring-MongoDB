package com.example.moviesbackend.repository;

import com.example.moviesbackend.model.entity.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Movie findMovieByTitle(String title);

    Optional<Movie> findMovieByImdbId(String imdbId);
}
