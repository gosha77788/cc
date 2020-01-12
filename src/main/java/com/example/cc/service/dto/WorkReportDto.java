package com.example.cc.service.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class WorkReportDto {

    private Long id;
    private EmployeeDto employee;
    private ProductDto product;
    private LocalDateTime doneAt;
    private Long amount;
}
