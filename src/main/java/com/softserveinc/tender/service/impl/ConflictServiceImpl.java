package com.softserveinc.tender.service.impl;


import com.softserveinc.tender.entity.CheckedStatus;
import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.entity.ConflictStatus;
import com.softserveinc.tender.repo.ConflictRepository;
import com.softserveinc.tender.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConflictServiceImpl implements ConflictService{

    @Autowired
    private ConflictRepository conflictRepository;

    @Override
    public List<Conflict> findAll() {
        return conflictRepository.findAll();
    }

    @Override
    public Conflict findById(int id) {
        return conflictRepository.findOne(id);
    }

}