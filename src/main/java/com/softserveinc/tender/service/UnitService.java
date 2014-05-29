package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Unit;
import java.util.List;

public interface UnitService {
    List<Unit> findAll();
    List<Unit> findUnits(int id);
}
