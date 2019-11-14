package com.example.cc.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkReportDto {

    private Long id;
    private EmployeeDto employee;
    private ProductDto product;
    private Date doneAt;
    private Long amount;
}
