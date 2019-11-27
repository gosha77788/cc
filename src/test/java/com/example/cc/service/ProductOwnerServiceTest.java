package com.example.cc.service;

import com.example.cc.model.ProductOwner;
import com.example.cc.repository.ProductOwnerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductOwnerServiceTest {

    @InjectMocks
    private ProductOwnerService productOwnerService;

    @Mock
    private ProductOwnerRepository productOwnerRepository;

    private ProductOwner buildProductOwner() {
        ProductOwner productOwner = new ProductOwner();
        productOwner.setId(1L);
        return productOwner;
    }

    @Test
    void testGetAll() {
        ProductOwner productOwner = buildProductOwner();
        List<ProductOwner> productOwners = new ArrayList<>();
        productOwners.add(productOwner);

        when(productOwnerRepository.findAll()).thenReturn(productOwners);
        List<ProductOwner> allProductOwner = productOwnerService.getAll();
        assertEquals(productOwners, allProductOwner);
        verify(productOwnerRepository, times(1)).findAll();
    }

    @Test
    void testGetProductOwner() {
        ProductOwner productOwner = buildProductOwner();

        when(productOwnerRepository.findById(productOwner.getId())).thenReturn(Optional.of(productOwner));
        ProductOwner actualProductOwner = productOwnerService.getProductOwner(productOwner.getId());
        assertEquals(productOwner, actualProductOwner);
        verify(productOwnerRepository, times(1)).findById(productOwner.getId());
    }

    @Test
    void testSaveProductOwner() {
        ProductOwner productOwner = buildProductOwner();

        when(productOwnerRepository.save(productOwner)).thenReturn(null);
        productOwnerService.saveProductOwner(productOwner);
        verify(productOwnerRepository, times(1)).save(productOwner);
    }
}