package com.example.cc.controller;

import com.example.cc.dto.LocationDto;
import com.example.cc.exception.CreatedEntityIdException;
import com.example.cc.exception.UpdatedEntityIdException;
import com.example.cc.mapper.LocationMapper;
import com.example.cc.service.LocationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationMapper locationMapper;
    private final LocationService locationService;

    public LocationController(LocationMapper locationMapper, LocationService locationService) {
        this.locationMapper = locationMapper;
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getAll() {
        return locationMapper.toDtos(locationService.getAll());
    }

    @GetMapping("{id}")
    public LocationDto getLocation(@PathVariable Long id) {
        return locationMapper.toDto(locationService.getLocation(id));
    }

    @PostMapping
    public void saveLocation(@RequestBody LocationDto locationDto) throws CreatedEntityIdException {
        if (locationDto.getId() != null) {
            throw new CreatedEntityIdException();
        }
        locationService.saveLocation((locationMapper.toEntity(locationDto)));
    }

    @PutMapping
    public void updateLocation(@RequestBody LocationDto locationDto) throws UpdatedEntityIdException {
        if (locationDto.getId() == null) {
            throw new UpdatedEntityIdException();
        }
        locationService.saveLocation((locationMapper.toEntity(locationDto)));
    }
}
