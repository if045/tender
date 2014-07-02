package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.repo.ConflictRepository;
import com.softserveinc.tender.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConflictServiceImpl implements ConflictService{

    @Autowired
    private ConflictRepository conflictRepository;

    @Override
    public List<Conflict> findAllByModeratorId(Integer id) {
        return conflictRepository.findByModeratorId(id);
    }

    @Override
    public Conflict findById(int id) {
        return conflictRepository.findOne(id);
    }
    @Override
    public Conflict save(Conflict conflict){
        return conflictRepository.save(conflict);
    }

}
