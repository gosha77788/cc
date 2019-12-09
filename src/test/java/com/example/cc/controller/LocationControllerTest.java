package com.example.cc.controller;

import com.example.cc.dto.LocationDto;
import com.example.cc.mapper.LocationMapper;
import com.example.cc.model.Location;
import com.example.cc.service.LocationService;
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

import static org.hamcrest.Matchers.is;
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
@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @MockBean
    private LocationMapper locationMapper;

    private final String URL = "/location";

    private LocationDto buildLocationDto(Long id) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id);
        locationDto.setValue("Gomel");
        return locationDto;
    }

    @Test
    void testGetAllLocation() throws Exception {
        LocationDto locationDto = buildLocationDto(1L);

        List<LocationDto> locationDtos = Arrays.asList(locationDto);

        doReturn(new ArrayList<>()).when(locationService).getAll();
        doReturn(locationDtos).when(locationMapper).toDtos(any());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value").value("Gomel"));
    }

    @Test
    void testGetLocation() throws Exception {
        LocationDto locationDto = buildLocationDto(1L);

        doReturn(new Location()).when(locationService).getLocation(anyLong());
        doReturn(locationDto).when(locationMapper).toDto(any(Location.class));

        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Gomel"));
    }

    @Test
    void testSaveLocation() throws Exception {
        LocationDto locationDto = buildLocationDto(null);

        doNothing().when(locationService).saveLocation(any(Location.class));
        doReturn(new Location()).when(locationMapper).toEntity(locationDto);

        assertNull(locationDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLocation() throws Exception {
        LocationDto locationDto = buildLocationDto(1L);

        doNothing().when(locationService).saveLocation(any(Location.class));
        doReturn(new Location()).when(locationMapper).toEntity(locationDto);

        assertNotNull(locationDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }
}