package org.example.mongodbcourse.repository;

import org.example.mongodbcourse.model.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
