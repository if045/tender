package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.MeasurementDto;
import com.softserveinc.tender.facade.MeasurementServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementServiceFacade measurementServiceFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<MeasurementDto> findMeasurements() {
        return measurementServiceFacade.findMeasurements();
    }
}
