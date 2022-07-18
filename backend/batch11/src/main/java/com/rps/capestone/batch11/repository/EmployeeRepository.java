package com.rps.capestone.batch11.repository;

import com.rps.capestone.batch11.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,String> {
    @Override
    void delete(Employee deleted);
}
