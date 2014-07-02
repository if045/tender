package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Unit;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UnitService {
    List<Unit> findAll();
    Unit save(Unit unit);
    List<Unit> findUnitsByTenderId(int tenderId, Pageable pageable);
    Unit findById(Integer id);
}
