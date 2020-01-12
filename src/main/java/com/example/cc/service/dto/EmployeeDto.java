package com.example.cc.service.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    private String fullName;
    private LocationDto location;
}
