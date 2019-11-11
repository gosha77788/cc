package com.example.cc.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOwnerDto {

    private Long id;
    private String fullName;
    private String shortName;
    private Long upn;

}
