package com.example.cc.service;

import com.example.cc.model.Product;
import com.example.cc.repository.ProductRepository;
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
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product buildProduct() {
        Product product = new Product();
        product.setId(1L);
        return product;
    }

    @Test
    void testGetProduct() {
        Product product = buildProduct();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product actualProduct = productService.getProduct(product.getId());
        assertEquals(product, actualProduct);
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testSaveProduct() {
        Product product = buildProduct();

        when(productRepository.save(product)).thenReturn(null);
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetAll() {
        Product product = buildProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);
        productService.getAll();
        verify(productRepository, times(1)).findAll();
    }
}
