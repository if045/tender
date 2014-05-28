package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.ConflictStatus;
import com.softserveinc.tender.repo.ConflictStatusRepository;
import com.softserveinc.tender.service.ConflictStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictStatusServiceImpl implements ConflictStatusService{

    @Autowired
    private ConflictStatusRepository conflictStatusRepository;

    @Override
    public List<ConflictStatus> findAll() {
        return conflictStatusRepository.findAll();
    }

    @Override
    public ConflictStatus findById(int id) {
        return conflictStatusRepository.findOne(id);
    }
}
