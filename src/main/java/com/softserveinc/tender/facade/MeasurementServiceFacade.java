package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.MeasurementDto;

import java.util.List;

public interface MeasurementServiceFacade {
    List<MeasurementDto> findMeasurements();
}
