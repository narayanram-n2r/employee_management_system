package com.rps.capestone.batch11.repository;

import com.rps.capestone.batch11.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository <Role,String>{
    Role findByRole(String role) ;

}
