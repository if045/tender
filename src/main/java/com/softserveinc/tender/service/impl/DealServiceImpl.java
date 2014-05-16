package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.repo.DealRepository;
import com.softserveinc.tender.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DealServiceImpl implements DealService{

    @Autowired
    private DealRepository dealRepository;

    @Override
    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    @Override
    public Deal findDealById(int id) {
        return dealRepository.findOne(id);
    }

    @Override
    public void saveDeal(Deal deal) {
        dealRepository.save(deal);
    }


}
