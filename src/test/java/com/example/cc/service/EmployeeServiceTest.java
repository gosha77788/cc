package com.example.cc.service;

import com.example.cc.model.Employee;
import com.example.cc.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFullName("Yura");
        return employee;
    }

    @Test
    void testSaveEmployee() {
        Employee employee = buildEmployee();

        when(employeeRepository.save(employee)).thenReturn(null);
        employeeService.saveEmployee(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployee() {
        Employee employee = buildEmployee();

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Employee actualEmployee = employeeService.getEmployee(employee.getId());
        assertEquals(employee, actualEmployee);
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    void testGetAll() {
        List<Employee> employees = new ArrayList<>();
        employees.add(buildEmployee());

        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> allEmployee = employeeService.getAll();
        assertEquals(employees, allEmployee);
        verify(employeeRepository, times(1)).findAll();
    }
}