package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.MeasurementDto;
import com.softserveinc.tender.facade.MeasurementServiceFacade;
import com.softserveinc.tender.service.MeasurementService;
import com.softserveinc.tender.util.Convertible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("measurementServiceFacade")
@Transactional
public class MeasurementServiceFacadeImpl implements MeasurementServiceFacade{

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private Convertible convertible;

    @Override
    public List<MeasurementDto> findMeasurements() {
        return convertible.mapObjects(measurementService.findAll(), MeasurementDto.class);
    }
}
