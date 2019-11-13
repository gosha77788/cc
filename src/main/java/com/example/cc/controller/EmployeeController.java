package com.example.cc.controller;

import com.example.cc.dto.EmployeeDto;
import com.example.cc.exception.CreatedEntityIdException;
import com.example.cc.exception.UpdatedEntityIdException;
import com.example.cc.mapper.EmployeeMapper;
import com.example.cc.service.EmployeeService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeMapper employeeMapper, EmployeeService employeeService) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployee() {
        return employeeMapper.toDtos(employeeService.getAll());
    }

    @GetMapping("{id}")
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return employeeMapper.toDto(employeeService.findEmployee(id));
    }

    @PostMapping
    public void saveEmployee(@RequestBody EmployeeDto employeeDto) throws CreatedEntityIdException {
        if (employeeDto.getId() != null) {
            throw new CreatedEntityIdException();
        }
        employeeService.saveEmployee(employeeMapper.toEntity(employeeDto));

    }

    @PutMapping
    public void updateEmployee(@RequestBody EmployeeDto employeeDto) throws UpdatedEntityIdException {
        if (employeeDto.getId() == null) {
            throw new UpdatedEntityIdException();
        }
        employeeService.saveEmployee(employeeMapper.toEntity(employeeDto));
    }
}
