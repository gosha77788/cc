package com.example.cc.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDto {

    private Long id;
    private LocalDate startAt;
    private LocalDate stopAt;
    private ProductDto product;
    private Long value;
}
