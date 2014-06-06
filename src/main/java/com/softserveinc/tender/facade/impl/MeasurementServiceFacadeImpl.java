package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.MeasurementDto;
import com.softserveinc.tender.facade.MeasurementServiceFacade;
import com.softserveinc.tender.service.MeasurementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service("measurementServiceFacade")
@Transactional
public class MeasurementServiceFacadeImpl implements MeasurementServiceFacade{

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MeasurementDto> findMeasurements() {
        Type targetListType = new TypeToken<List<MeasurementDto>>(){}.getType();
        return modelMapper.map(measurementService.findAll(), targetListType);
    }
}
