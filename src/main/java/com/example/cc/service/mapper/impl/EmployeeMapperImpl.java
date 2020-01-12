package com.example.cc.service.mapper.impl;

import com.example.cc.service.dto.EmployeeDto;
import com.example.cc.service.mapper.EmployeeMapper;
import com.example.cc.service.mapper.LocationMapper;
import com.example.cc.model.Employee;
import com.example.cc.service.LocationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {

    private final LocationMapper locationMapper;
    private final LocationService locationService;

    public EmployeeMapperImpl(LocationMapper locationMapper, LocationService locationService) {
        this.locationMapper = locationMapper;
        this.locationService = locationService;
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFullName(employee.getFullName());
        dto.setLocation(locationMapper.toDto(employee.getLocation()));
        return dto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFullName(dto.getFullName());
        employee.setLocation(locationService.getLocation(dto.getLocation().getId()));
        return employee;
    }

    @Override
    public List<EmployeeDto> toDtos(List<Employee> employees) {
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }
}
