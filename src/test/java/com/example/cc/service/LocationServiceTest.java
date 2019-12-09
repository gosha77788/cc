package com.example.cc.service;

import com.example.cc.model.Location;
import com.example.cc.repository.LocationRepository;
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
class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    private Location buildLocation() {
        Location location = new Location();
        location.setId(1L);
        return location;
    }

    @Test
    void testGetAll() {
        Location location = buildLocation();
        List<Location> locations = new ArrayList<>();
        locations.add(location);

        when(locationRepository.findAll()).thenReturn(locations);
        List<Location> allLocation = locationService.getAll();
        assertEquals(locations, allLocation);
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void testGetLocation() {
        Location location = buildLocation();

        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));
        Location actualLocation = locationService.getLocation(location.getId());
        assertEquals(location, actualLocation);
        verify(locationRepository, times(1)).findById(location.getId());
    }

    @Test
    void testSaveLocation() {
        Location location = buildLocation();

        when(locationRepository.save(location)).thenReturn(null);
        locationService.saveLocation(location);
        verify(locationRepository, times(1)).save(location);
    }
}
