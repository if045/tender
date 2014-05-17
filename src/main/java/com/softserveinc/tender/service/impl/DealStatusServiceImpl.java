package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.DealStatus;
import com.softserveinc.tender.repo.DealStatusRepository;
import com.softserveinc.tender.service.DealStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DealStatusServiceImpl implements DealStatusService{

    @Autowired
    private DealStatusRepository dealStatusRepository;

    @Override
    public List<DealStatus> findAll() {
        return dealStatusRepository.findAll();
    }

    @Override
    public DealStatus findDealStatusById(int id) {
        return dealStatusRepository.findOne(id);
    }
}
