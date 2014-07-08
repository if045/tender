package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.repo.ConflictRepository;
import com.softserveinc.tender.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConflictServiceImpl implements ConflictService {

    public static final String PERCENT = "%";

    @Autowired
    private ConflictRepository conflictRepository;

    @Override
    public List<Conflict> findAllByModeratorId(Integer id, Pageable pageable, String searchParam) {
        Integer searchFlag = (searchParam == null) ? 1 : 0;
        if (searchFlag == 0) {
            searchParam = PERCENT + searchParam.toLowerCase().trim() + PERCENT;
        }
        return conflictRepository.findConflicts(id, searchParam, searchFlag, pageable);
    }

    @Override
    public Conflict findById(int id) {
        return conflictRepository.findOne(id);
    }

    @Override
    public Conflict save(Conflict conflict) {
        return conflictRepository.save(conflict);
    }

    @Override
    public Long getConflictsNumber(Integer id, String searchParam) {
        Integer searchFlag = (searchParam == null) ? 1 : 0;
        if (searchFlag == 0) {
            searchParam = PERCENT + searchParam.toLowerCase().trim() + PERCENT;
        }
        return conflictRepository.getConflictsNumber(id, searchParam, searchFlag);
    }

}
