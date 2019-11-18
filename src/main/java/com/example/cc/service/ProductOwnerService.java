package com.example.cc.service;

import com.example.cc.model.ProductOwner;
import com.example.cc.repository.ProductOwnerRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductOwnerService {

    private final ProductOwnerRepository productOwnerRepository;

    public ProductOwnerService(ProductOwnerRepository productOwnerRepository) {
        this.productOwnerRepository = productOwnerRepository;
    }

    public List<ProductOwner> getAll() {
        return productOwnerRepository.findAll();
    }

    public ProductOwner getProductOwner(Long id) {
        return productOwnerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void saveProductOwner(ProductOwner productOwner) {
        productOwnerRepository.save(productOwner);
    }
}
