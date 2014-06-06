package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.facade.LocationServiceFacade;
import com.softserveinc.tender.service.LocationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service("locationServiceFacade")
@Transactional
public class LocationServiceFacadeImpl implements LocationServiceFacade{

    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LocationDto> findLocations() {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locationService.findAll(), targetListType);
    }
}
