package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Location;
import java.util.List;

public interface LocationService {

    Location save(Location location);
    Location findById(int id);
    List<Location> findAll();
    void deleteById(int id);
    List<Location> getTendersLocation();
}
