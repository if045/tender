package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.facade.LocationServiceFacade;
import com.softserveinc.tender.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("locationServiceFacade")
public class LocationServiceFacadeImpl implements LocationServiceFacade {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

    public List<LocationDto> findLocation() {
        List<LocationDto> locationDto = new ArrayList<>();
        for (Location location : locationService.getTendersLocation()) {
            locationDto.add(modelMapper.map(location, LocationDto.class));
        }
        return locationDto;
    }


}
