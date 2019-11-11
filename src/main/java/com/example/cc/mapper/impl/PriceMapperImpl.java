package com.example.cc.mapper.impl;


import com.example.cc.dto.PriceDto;
import com.example.cc.mapper.PriceMapper;
import com.example.cc.mapper.ProductMapper;
import com.example.cc.model.Price;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PriceMapperImpl implements PriceMapper {

    private final ProductMapper productMapper;

    public PriceMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public PriceDto toDto(Price price) {
        PriceDto dto = new PriceDto();
        dto.setId(price.getId());
        dto.setStartAt(price.getStartAt());
        dto.setStopAt(price.getStopAt());
        dto.setProduct(productMapper.toDto(price.getProduct()));
        dto.setValue(price.getValue());
        return dto;
    }

    @Override
    public Price toEntity(PriceDto dto) {
        Price price = new Price();
        price.setId(dto.getId());
        price.setStartAt(dto.getStartAt());
        price.setStopAt(dto.getStopAt());
        price.setProduct(productMapper.toEntity(dto.getProduct()));
        price.setValue(dto.getValue());
        return price;
    }

    @Override
    public List<PriceDto> toDtos(List<Price> prices) {
        return prices.stream().map(this::toDto).collect(Collectors.toList());
    }
}
