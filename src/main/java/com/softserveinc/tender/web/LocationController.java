package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.facade.LocationServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationServiceFacade locationServiceFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<LocationDto> findLocations() {
        return locationServiceFacade.findLocations();
    }
}
