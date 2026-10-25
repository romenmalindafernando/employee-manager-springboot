package com.example.employeemanager.controller;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display all employees and the registration form
    @GetMapping
    public String listEmployees(Model model) {

        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());

        return "employees";
    }

    // Register a new employee
    @PostMapping
    public String addEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute(
                "employees",
                employeeService.getAllEmployees()
            );
            return "employees";
        }

        try {
            employeeService.saveEmployee(employee);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "duplicate", e.getMessage());
            model.addAttribute(
                "employees",
                employeeService.getAllEmployees()
            );
            return "employees";
        }

        return "redirect:/employees";
    }

    // Display employee edit form
    @GetMapping("/{id}/edit")
    public String editEmployee(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeService.getEmployeeById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Employee not found with ID: " + id
            ));

        model.addAttribute("employee", employee);

        return "edit-employee";
    }

    // Update employee information
    @PostMapping("/{id}/update")
    public String updateEmployee(
            @PathVariable Long id,
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult result) {

        if (result.hasErrors()) {
            return "edit-employee";
        }

        try {
            employeeService.updateEmployee(id, employee);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "duplicate", e.getMessage());
            return "edit-employee";
        }

        return "redirect:/employees";
    }

    // Delete employee
    @PostMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }
}