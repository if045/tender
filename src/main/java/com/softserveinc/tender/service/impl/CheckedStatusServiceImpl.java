package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.CheckedStatus;
import com.softserveinc.tender.repo.CheckedStatusRepository;
import com.softserveinc.tender.service.CheckedStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CheckedStatusServiceImpl implements CheckedStatusService {

    @Autowired
    private CheckedStatusRepository checkedStatusRepository;

    @Override
    public List<CheckedStatus> findAll() {
        return checkedStatusRepository.findAll();
    }

    @Override
    public CheckedStatus findById(int id) {
        return checkedStatusRepository.findOne(id);
    }
}
