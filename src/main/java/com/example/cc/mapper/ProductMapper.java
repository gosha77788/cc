package com.example.cc.mapper;

import com.example.cc.dto.ProductDto;
import com.example.cc.model.Product;
import java.util.List;

public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);

    List<ProductDto> toDtos(List<Product> products);
}
