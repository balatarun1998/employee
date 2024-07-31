package com.myapp.employee.service;

import com.myapp.employee.entity.EmployeeDetails;

public interface EmployeeService {

    EmployeeDetails saveEmployee(EmployeeDetails employee);
    EmployeeDetails getEmployeeById(String employeeId);

    double calculateTax(double annualSalary);

    double calculateCess(double annualSalary);
}
