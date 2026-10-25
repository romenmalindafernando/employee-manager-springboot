package com.example.employeemanager.service;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Retrieve an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Save a new employee
    @Transactional
    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException(
                "An employee with this email already exists."
            );
        }

        return employeeRepository.save(employee);
    }

    // Update an existing employee
    @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Employee not found with ID: " + id
            ));

        if (employeeRepository.existsByEmailAndIdNot(
                updatedEmployee.getEmail(), id)) {
            throw new IllegalArgumentException(
                "An employee with this email already exists."
            );
        }

        employee.setFullName(updatedEmployee.getFullName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setPhone(updatedEmployee.getPhone());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(employee);
    }

    // Delete an employee
    @Transactional
    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException(
                "Employee not found with ID: " + id
            );
        }

        employeeRepository.deleteById(id);
    }
}