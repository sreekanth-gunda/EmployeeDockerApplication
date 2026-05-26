package com.employee.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.docker.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
