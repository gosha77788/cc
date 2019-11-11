package com.example.cc.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkReportDto {

    private Long id;
    private EmployeeDto employee;
    private ProductDto product;
    private LocalDate doneAt;
    private Long amount;
}
