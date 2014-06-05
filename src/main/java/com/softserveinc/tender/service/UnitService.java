package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Unit;
import java.util.List;

public interface UnitService {
    List<Unit> findAll();
    Unit save(Unit unit);
    List<Unit> findUnitsByTenderId(int tenderId);
}
