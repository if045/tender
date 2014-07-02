package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Location;
import java.util.List;

public interface LocationService {

    Location save(Location location);
    Location findById(Integer id);
    List<Location> findAll();
    void deleteById(Integer id);
    List<Location> getTendersLocations();
    List<Location> getLocationsByIds(List<Integer> locations);
}
