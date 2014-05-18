package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.*;
import java.util.List;

public interface LocationService {
    public Location save(Location location);
    public Location findById(int id);
    public List<Location> findAll();
    public Location update(Location location);
    public void deleteById(int id);
}
