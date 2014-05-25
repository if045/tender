package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.service.*;
import com.softserveinc.tender.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepo locationRepo;

    @Override
    public Location save(Location location) {
        return locationRepo.save(location);
    }

    @Override
    public Location findById(int id) {
        return locationRepo.findOne(id);
    }

    @Override
    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    @Override
    public void deleteById(int id) {
        locationRepo.delete(id);
    }

    @Override
    public List<Location> getTendersLocation() {
        return locationRepo.getTendersLocation();
    }
}
