package com.nzangi.backend.repository;

import com.nzangi.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    // All CRUD database methods for interacting with db
}
