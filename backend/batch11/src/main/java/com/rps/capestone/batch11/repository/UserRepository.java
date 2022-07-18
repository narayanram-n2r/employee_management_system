package com.rps.capestone.batch11.repository;

import com.rps.capestone.batch11.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
