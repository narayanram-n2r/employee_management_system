package com.rps.capestone.batch11.controller;

import com.rps.capestone.batch11.model.Employee;
import com.rps.capestone.batch11.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;




//@CrossOrigin(origins = "http://localhost:4200)
@RestController

public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method=RequestMethod.GET, value="/api/employees")
    public Iterable<Employee> employee() {
        return employeeRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/api/employees")
    public String save(@RequestBody Employee employee) {
        employeeRepository.save(employee);

        return employee.getEmpId();
    }

    @RequestMapping(method=RequestMethod.GET, value="/api/employees/{id}")
    public Optional<Employee> show(@PathVariable String id) {
        return employeeRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/api/employees/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(employee.getEmpname() != null)
            emp.get().setEmpname(employee.getEmpname());
        if(employee.getEmpId() != null)
            emp.get().setEmpId(employee.getEmpId());
        if(employee.getEmpemailId() != null)
            emp.get().setEmpemailIdEmailId(employee.getEmpemailId());
        if(employee.getEmpsalary() != 0)
            emp.get().setEmpSalary(emp.get().getEmpsalary());
        employeeRepository.save(emp.get());
        return emp.get();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/api/employees/{id}")
    public String delete(@PathVariable String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employeeRepository.delete(employee.get());

        return "employee deleted";
    }
}









