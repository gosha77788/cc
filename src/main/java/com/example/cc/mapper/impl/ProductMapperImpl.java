package com.example.cc.mapper.impl;

import com.example.cc.dto.ProductDto;
import com.example.cc.mapper.ProductMapper;
import com.example.cc.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setShortName(product.getShortName());
        dto.setDescription(product.getDescription());
        dto.setCode(product.getCode());
        return dto;
    }

    @Override
    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setShortName(dto.getShortName());
        product.setDescription(dto.getDescription());
        product.setCode(dto.getCode());
        return product;
    }

    @Override
    public List<ProductDto> toDtos(List<Product> products) {
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }
}
