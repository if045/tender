package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Measurement;
import java.util.List;

public interface MeasurementService {

    List<Measurement> findAll();
    Measurement findMeasurementById(Integer id);
    void saveMeasurement(Measurement measurement);
}
