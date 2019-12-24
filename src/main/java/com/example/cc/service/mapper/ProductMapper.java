package com.example.cc.service.mapper;

import com.example.cc.service.dto.ProductDto;
import com.example.cc.model.Product;
import java.util.List;

public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);

    List<ProductDto> toDtos(List<Product> products);
}
