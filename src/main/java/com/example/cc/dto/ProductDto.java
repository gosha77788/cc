package com.example.cc.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String shortName;
    private String description;
    private String code;

}
