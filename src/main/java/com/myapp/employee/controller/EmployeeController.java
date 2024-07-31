package com.myapp.employee.controller;

import com.myapp.employee.entity.EmployeeDetails;
import com.myapp.employee.model.TaxDeduction;
import com.myapp.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "insert employee details")
    public ResponseEntity<String> storeEmployeeDetails(@Valid @RequestBody EmployeeDetails employeeDetails) {
        employeeService.saveEmployee(employeeDetails);
        return new ResponseEntity<>("Employee saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/tax-deductions")
    @Operation(summary = "tax deductions calculation")
    public ResponseEntity<?> getTaxDeductions(@PathVariable String employeeId) {
        EmployeeDetails employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        double yearlySalary = employee.getSalary() * 12;
        double taxAmount = employeeService.calculateTax(yearlySalary);
        double cessAmount = employeeService.calculateCess(yearlySalary);

        return ResponseEntity.ok(new TaxDeduction(employeeId, employee.getFirstName(), employee.getLastName(), yearlySalary, taxAmount, cessAmount));
    }
}
