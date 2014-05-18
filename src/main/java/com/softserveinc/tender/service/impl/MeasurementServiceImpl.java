package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Measurement;
import com.softserveinc.tender.repo.MeasurementRepository;
import com.softserveinc.tender.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Measurement findMeasurementById(int id) {
        return measurementRepository.findOne(id);
    }

    @Override
    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}
