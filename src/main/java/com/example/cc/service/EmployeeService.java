package com.example.cc.service;

import com.example.cc.model.Employee;
import com.example.cc.repository.EmployeeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee findEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
