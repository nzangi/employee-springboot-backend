package com.nzangi.backend.controllers;

import com.nzangi.backend.exception.ResourceNotFoundException;
import com.nzangi.backend.model.Employee;
import com.nzangi.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    // Creat an employee REST API
    @PostMapping
    public Employee createNewEmployee(@RequestBody  Employee employee){
        return employeeRepository.save(employee);

    }
    // Get an employee REST API by ID
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id){
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exists with ID:"+id));
        return ResponseEntity.ok(employee);
    }
}
