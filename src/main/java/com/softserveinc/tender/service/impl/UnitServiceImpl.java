package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.repo.UnitRepository;
import com.softserveinc.tender.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    public List<Unit> findAll(){
        return unitRepository.findAll();
    }

    @Override
    public Unit save(Unit unit) {
        return unitRepository.saveAndFlush(unit);
    }

    public List<Unit> findUnitsByTenderId(int tenderId, Pageable pageable) {
        return unitRepository.findUnitsByTenderId(tenderId, pageable);
    }

    @Override
    public Unit findById(Integer id) {
        return unitRepository.findOne(id);
    }
}
