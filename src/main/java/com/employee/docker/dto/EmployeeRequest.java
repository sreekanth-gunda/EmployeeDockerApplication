package com.employee.docker.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;
}