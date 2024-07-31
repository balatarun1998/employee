package com.myapp.employee.service.implementation;

import com.myapp.employee.entity.EmployeeDetails;
import com.myapp.employee.repository.EmployeeDetailsRepository;
import com.myapp.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDetailsRepository employeeRepository;

    @Override
    public EmployeeDetails saveEmployee(EmployeeDetails employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeDetails getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public double calculateTax(double salary) {
        double taxAmount = 0.0;

        if (salary <= 250000) {
            return taxAmount;
        } else if (salary <= 500000) {
            taxAmount = (salary - 250000) * 0.05;
        } else if (salary <= 1000000) {
            taxAmount = (500000 - 250000) * 0.05
                    + (salary - 500000) * 0.10;
        } else {
            taxAmount = (500000 - 250000) * 0.05
                    + (1000000 - 500000) * 0.10
                    + (salary - 1000000) * 0.20;
        }

        return taxAmount;
    }

    @Override
    public double calculateCess(double annualSalary) {
        double excessAmount = Math.max(0, annualSalary - 2500000);
        return excessAmount * 0.02;
    }

}
