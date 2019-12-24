package com.example.cc.service.mapper;

import com.example.cc.service.dto.ProductOwnerDto;
import com.example.cc.model.ProductOwner;
import java.util.List;

public interface ProductOwnerMapper {

    ProductOwnerDto toDto(ProductOwner productOwner);

    ProductOwner toEntity(ProductOwnerDto dto);

    List<ProductOwnerDto> toDtos(List<ProductOwner> products);
}
