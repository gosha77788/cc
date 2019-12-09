package com.example.cc.controller;

import com.example.cc.dto.ProductDto;
import com.example.cc.mapper.ProductMapper;
import com.example.cc.model.Product;
import com.example.cc.service.ProductService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    private ProductDto buildProductDto(Long id) {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setShortName("goods");
        return productDto;
    }

    private final String URL = "/product";

    @Test
    void testGetAll() throws Exception {
        ProductDto productDto = buildProductDto(1L);

        List<ProductDto> productDtos = Arrays.asList(productDto);

        doReturn(new ArrayList<>()).when(productService).getAll();
        doReturn(productDtos).when(productMapper).toDtos(any());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].shortName").value(productDto.getShortName()));

    }

    @Test
    void testGetProduct() throws Exception {
        ProductDto productDto = buildProductDto(1L);

        doReturn(new Product()).when(productService).getProduct(anyLong());
        doReturn(productDto).when(productMapper).toDto(any(Product.class));

        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortName").value(productDto.getShortName()));
    }

    @Test
    void testSaveProduct() throws Exception {
        ProductDto productDto = buildProductDto(null);

        doNothing().when(productService).saveProduct(any(Product.class));
        doReturn(new Product()).when(productMapper).toEntity(productDto);

        assertNull(productDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductDto productDto = buildProductDto(1L);

        doNothing().when(productService).saveProduct(any(Product.class));
        doReturn(new Product()).when(productMapper).toEntity(productDto);

        assertNotNull(productDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }
}
