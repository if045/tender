package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.facade.LocationServiceFacade;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.util.Convertible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("locationServiceFacade")
@Transactional
public class LocationServiceFacadeImpl implements LocationServiceFacade{

    @Autowired
    private LocationService locationService;

    @Autowired
    private Convertible convertible;

    @Override
    public List<LocationDto> findLocations() {
        return convertible.mapObjects(locationService.findAll(), LocationDto.class);
    }
}
