package com.example.cc.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkReportDto {

    private Long id;
    private EmployeeDto employee;
    private ProductDto product;
    private LocalDateTime doneAt;
    private Long amount;
}
