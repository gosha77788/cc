package com.example.cc.service.mapper;

import com.example.cc.service.dto.LocationDto;
import com.example.cc.model.Location;
import java.util.List;

public interface LocationMapper {

    Location toEntity(LocationDto locationDto);

    LocationDto toDto(Location location);

    List<LocationDto> toDtos(List<Location> locations);
}
