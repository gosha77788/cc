package com.example.cc.controller;

import com.example.cc.dto.ProductDto;
import com.example.cc.mapper.ProductMapper;
import com.example.cc.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productMapper.toDtos(productService.getAll());
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productMapper.toDto(productService.getProduct(id));
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.toEntity(productDto));
    }
}
