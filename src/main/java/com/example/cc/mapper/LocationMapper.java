package com.example.cc.mapper;

import com.example.cc.dto.LocationDto;
import com.example.cc.model.Location;
import java.util.List;

public interface LocationMapper {

    Location toEntity(LocationDto locationDto);

    LocationDto toDto(Location location);

    List<LocationDto> toDtos(List<Location> locations);
}
