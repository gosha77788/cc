package com.example.cc.mapper;

import com.example.cc.dto.PriceDto;
import com.example.cc.model.Price;
import java.util.List;

public interface PriceMapper {

    PriceDto toDto(Price price);

    Price toEntity(PriceDto dto);

    List<PriceDto> toDtos(List<Price> prices);
}
