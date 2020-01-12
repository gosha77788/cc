package com.example.cc.service.mapper.impl;

import com.example.cc.service.dto.ProductOwnerDto;
import com.example.cc.service.mapper.ProductOwnerMapper;
import com.example.cc.model.ProductOwner;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductOwnerMapperImpl implements ProductOwnerMapper {

    @Override
    public ProductOwnerDto toDto(ProductOwner productOwner) {
        ProductOwnerDto productOwnerDto = new ProductOwnerDto();
        productOwnerDto.setId(productOwner.getId());
        productOwnerDto.setFullName(productOwner.getFullName());
        productOwnerDto.setShortName(productOwner.getShortName());
        productOwnerDto.setUpn(productOwner.getUpn());
        return productOwnerDto;
    }

    @Override
    public ProductOwner toEntity(ProductOwnerDto dto) {
        ProductOwner productOwner = new ProductOwner();
        productOwner.setId(dto.getId());
        productOwner.setFullName(dto.getFullName());
        productOwner.setShortName(dto.getShortName());
        productOwner.setUpn(dto.getUpn());
        return productOwner;
    }

    @Override
    public List<ProductOwnerDto> toDtos(List<ProductOwner> products) {
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }
}
