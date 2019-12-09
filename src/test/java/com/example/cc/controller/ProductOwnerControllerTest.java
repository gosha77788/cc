package com.example.cc.controller;

import com.example.cc.dto.ProductOwnerDto;
import com.example.cc.mapper.ProductOwnerMapper;
import com.example.cc.model.ProductOwner;
import com.example.cc.service.ProductOwnerService;
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
@WebMvcTest(ProductOwnerController.class)
class ProductOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductOwnerService productOwnerService;

    @MockBean
    private ProductOwnerMapper productOwnerMapper;

    private ProductOwnerDto buildProductOwnerDto(Long id) {
        ProductOwnerDto productOwnerDto = new ProductOwnerDto();
        productOwnerDto.setId(id);
        productOwnerDto.setFullName("goods");
        return productOwnerDto;
    }

    private final String URL = "/productowner";

    @Test
    void testGetAllProductOwner() throws Exception {
        ProductOwnerDto productOwnerDto = buildProductOwnerDto(1L);

        List<ProductOwnerDto> productOwnerDtos = Arrays.asList(productOwnerDto);

        doReturn(new ArrayList<>()).when(productOwnerService).getAll();
        doReturn(productOwnerDtos).when(productOwnerMapper).toDtos(any());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("goods"));
    }

    @Test
    void testGetProductOwner() throws Exception {
        ProductOwnerDto productOwnerDto = buildProductOwnerDto(1L);

        doReturn(new ProductOwner()).when(productOwnerService).getProductOwner(anyLong());
        doReturn(productOwnerDto).when(productOwnerMapper).toDto(any(ProductOwner.class));

        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("goods"));
    }

    @Test
    void testSaveProductOwner() throws Exception {
        ProductOwnerDto productOwnerDto = buildProductOwnerDto(null);

        doNothing().when(productOwnerService).saveProductOwner(any(ProductOwner.class));
        doReturn(new ProductOwner()).when(productOwnerMapper).toEntity(productOwnerDto);

        assertNull(productOwnerDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProductOwner() throws Exception {
        ProductOwnerDto productOwnerDto = buildProductOwnerDto(1L);

        doNothing().when(productOwnerService).saveProductOwner(any(ProductOwner.class));
        doReturn(new ProductOwner()).when(productOwnerMapper).toEntity(productOwnerDto);

        assertNotNull(productOwnerDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }
}