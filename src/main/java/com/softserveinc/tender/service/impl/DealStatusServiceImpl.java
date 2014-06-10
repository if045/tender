package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.DealStatus;
import com.softserveinc.tender.repo.DealStatusRepository;
import com.softserveinc.tender.service.DealStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DealStatusServiceImpl implements DealStatusService {

    @Autowired
    private DealStatusRepository dealStatusRepository;

    @Override
    public List<DealStatus> findAllDealStatuses() {
        return dealStatusRepository.findAll();
    }

    @Override
    public DealStatus findDealStatusById(Integer id) {
        return dealStatusRepository.findOne(id);
    }

    @Override
    public DealStatus findByName(String name) {
        return dealStatusRepository.findByName(name);
    }
}
