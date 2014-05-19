package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.repo.TenderStatusRepository;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TenderStatusServiceImpl implements TenderStatusService{

    @Autowired
    private TenderStatusRepository tenderStatusRepository;

    @Override
    public List<TenderStatus> findAll() {
        return tenderStatusRepository.findAll();
    }

    @Override
    public TenderStatus findDealStatusById(int id) {
        return tenderStatusRepository.findOne(id);
    }
}
