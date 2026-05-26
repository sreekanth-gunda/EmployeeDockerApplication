package com.employee.docker.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.employee.docker.dto.EmployeeRequest;
import com.employee.docker.entity.Employee;
import com.employee.docker.exception.ResourceNotFoundException;
import com.employee.docker.repository.EmployeeRepository;
import com.employee.docker.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    @Override
    public Employee create(EmployeeRequest req) {
        Employee e = new Employee();
        e.setName(req.getName());
        e.setEmail(req.getEmail());
        e.setDepartment(req.getDepartment());
        return repo.save(e);
    }

    @Override
    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Employee update(Long id, EmployeeRequest req) {
        Employee e = getById(id);
        e.setName(req.getName());
        e.setEmail(req.getEmail());
        e.setDepartment(req.getDepartment());
        return repo.save(e);
    }

    @Override
    public void delete(Long id) {
        Employee e = getById(id);
        repo.delete(e);
    }
}
