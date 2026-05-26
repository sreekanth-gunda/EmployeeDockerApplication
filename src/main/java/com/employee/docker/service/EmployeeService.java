package com.employee.docker.service;

import java.util.List;

import com.employee.docker.dto.EmployeeRequest;
import com.employee.docker.entity.Employee;

public interface EmployeeService {

    Employee create(EmployeeRequest request);

    Employee getById(Long id);

    List<Employee> getAll();

    Employee update(Long id, EmployeeRequest request);

    void delete(Long id);
}
