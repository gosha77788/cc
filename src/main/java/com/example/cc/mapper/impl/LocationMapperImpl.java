package com.example.cc.mapper.impl;

import com.example.cc.dto.LocationDto;
import com.example.cc.mapper.LocationMapper;
import com.example.cc.model.Location;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LocationMapperImpl implements LocationMapper {

    @Override
    public Location toEntity(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setValue(locationDto.getValue());
        return location;
    }

    @Override
    public LocationDto toDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setValue(location.getValue());
        return locationDto;
    }

    @Override
    public List<LocationDto> toDtos(List<Location> location) {
        return location.stream().map(this::toDto).collect(Collectors.toList());
    }
}
