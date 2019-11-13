package com.example.cc.controller;

import com.example.cc.dto.EmployeeDto;
import com.example.cc.exception.IdException;
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
    public void saveEmployee(@RequestBody EmployeeDto employeeDto) throws IdException {
        if (employeeDto.getId() == null) {
            employeeService.saveEmployee(employeeMapper.toEntity(employeeDto));
        } else {
            throw new IdException("При добавлении нового сотрудника ID не указывается", employeeDto.getId());
        }
    }

    @PutMapping
    public void updateProduct(@RequestBody EmployeeDto productDto) throws IdException {
        if (productDto.getId() != null) {
            employeeService.saveEmployee(employeeMapper.toEntity(productDto));
        } else {
            throw new IdException("При изменении сотрудника требуется указать ID", productDto.getId());
        }
    }
}
