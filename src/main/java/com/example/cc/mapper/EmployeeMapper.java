package com.example.cc.mapper;

import com.example.cc.dto.EmployeeDto;
import com.example.cc.model.Employee;
import java.util.List;

public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto dto);

    List<EmployeeDto> toDtos(List<Employee> employees);
}
