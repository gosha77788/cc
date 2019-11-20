package com.example.cc.service;

import com.example.cc.model.Location;
import com.example.cc.repository.LocationRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location getLocation(Long id) {
        return locationRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }
}
