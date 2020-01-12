package com.example.cc.service.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PriceDto {

    private Long id;
    private LocalDate startAt;
    private LocalDate stopAt;
    private ProductDto product;
    private Long value;
}
