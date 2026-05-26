package com.employee.docker.service;

import com.employee.docker.dto.EmployeeRequest;
import com.employee.docker.entity.Employee;
import com.employee.docker.exception.ResourceNotFoundException;
import com.employee.docker.repository.EmployeeRepository;
import com.employee.docker.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repo;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void testCreateEmployee() {
        EmployeeRequest req = new EmployeeRequest();
        req.setName("John");
        req.setEmail("john@gmail.com");
        req.setDepartment("IT");

        Employee saved = new Employee(1L, "John", "john@gmail.com", "IT");

        when(repo.save(any(Employee.class))).thenReturn(saved);

        Employee result = service.create(req);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testGetById() {
        Employee emp = new Employee(1L, "John", "john@gmail.com", "IT");

        when(repo.findById(1L)).thenReturn(Optional.of(emp));

        Employee result = service.getById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getById(1L));
    }

    @Test
    void testGetAll() {
        when(repo.findAll()).thenReturn(List.of(
                new Employee(1L, "A", "a@mail.com", "IT"),
                new Employee(2L, "B", "b@mail.com", "HR")
        ));

        List<Employee> result = service.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateEmployee() {
        Employee existing = new Employee(1L, "John", "john@gmail.com", "IT");

        EmployeeRequest req = new EmployeeRequest();
        req.setName("John Updated");
        req.setEmail("john2@gmail.com");
        req.setDepartment("HR");

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any(Employee.class))).thenReturn(existing);

        Employee result = service.update(1L, req);

        assertEquals("John Updated", result.getName());
    }

    @Test
    void testDeleteEmployee() {
        Employee emp = new Employee(1L, "John", "john@gmail.com", "IT");

        when(repo.findById(1L)).thenReturn(Optional.of(emp));
        doNothing().when(repo).delete(emp);

        assertDoesNotThrow(() -> service.delete(1L));

        verify(repo, times(1)).delete(emp);
    }
}