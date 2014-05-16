package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer>{
}
