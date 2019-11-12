package com.example.cc.mapper.impl;

import com.example.cc.dto.EmployeeDto;
import com.example.cc.mapper.EmployeeMapper;
import com.example.cc.model.Employee;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFullName(employee.getFullName());
        dto.setLocation(employee.getLocation());
        return dto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFullName(dto.getFullName());
        employee.setLocation(dto.getLocation());
        return employee;
    }

    @Override
    public List<EmployeeDto> toDtos(List<Employee> employees) {
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }
}
