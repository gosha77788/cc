package com.example.cc.controller;

import com.example.cc.dto.ProductOwnerDto;
import com.example.cc.exception.CreatedEntityIdException;
import com.example.cc.exception.UpdatedEntityIdException;
import com.example.cc.mapper.ProductOwnerMapper;
import com.example.cc.service.ProductOwnerService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productowner")
public class ProductOwnerController {

    private final ProductOwnerMapper productOwnerMapper;
    private final ProductOwnerService productOwnerService;

    public ProductOwnerController(ProductOwnerMapper productOwnerMapper, ProductOwnerService productOwnerService) {
        this.productOwnerMapper = productOwnerMapper;
        this.productOwnerService = productOwnerService;
    }

    @GetMapping
    public List<ProductOwnerDto> getAllProductOwner() {
        return productOwnerMapper.toDtos(productOwnerService.getAll());
    }

    @GetMapping("{id}")
    public ProductOwnerDto getProductOwner(@PathVariable Long id) {
        return productOwnerMapper.toDto(productOwnerService.getProductOwner(id));
    }

    @PostMapping
    public void saveProductOwner(@RequestBody ProductOwnerDto productOwnerDto) throws CreatedEntityIdException {
        if (productOwnerDto.getId() != null) {
            throw new CreatedEntityIdException();
        }
        productOwnerService.saveProductOwner(productOwnerMapper.toEntity(productOwnerDto));
    }

    @PutMapping
    public void updateProductOwner(@RequestBody ProductOwnerDto productOwnerDto) throws UpdatedEntityIdException {
        if (productOwnerDto.getId() == null) {
            throw new UpdatedEntityIdException();
        }
        productOwnerService.saveProductOwner(productOwnerMapper.toEntity(productOwnerDto));
    }
}
