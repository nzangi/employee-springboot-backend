package com.nzangi.backend.controllers;

import com.nzangi.backend.exception.ResourceNotFoundException;
import com.nzangi.backend.model.Employee;
import com.nzangi.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    // Update an employee REST API by ID
    @PostMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id:"+id)
        );

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);

    }
    // Delete an employee REST API by ID
    @DeleteMapping("{id}")

    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id){
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee doesnot exist with id:"+id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
