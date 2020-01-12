package com.example.cc.service.mapper;

import com.example.cc.service.dto.EmployeeDto;
import com.example.cc.model.Employee;
import java.util.List;

public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto dto);

    List<EmployeeDto> toDtos(List<Employee> employees);
}
