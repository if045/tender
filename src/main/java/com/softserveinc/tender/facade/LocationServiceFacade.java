package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.LocationDto;

import java.util.List;

public interface LocationServiceFacade {
    public List<LocationDto> findLocation();
}
