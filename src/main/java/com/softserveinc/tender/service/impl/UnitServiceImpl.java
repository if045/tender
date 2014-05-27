package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.repo.ItemRepository;
import com.softserveinc.tender.repo.UnitRepository;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Transactional
    public List<Unit> findAll(){
        return unitRepository.findAll();
    }

    @Override
    public List<Unit>findByTenderID(int id){
        return unitRepository.findByTenderID(id);
    }
}
