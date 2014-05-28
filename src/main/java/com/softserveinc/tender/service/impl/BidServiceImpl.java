package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.repo.BidRepository;
import com.softserveinc.tender.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService{

    @Autowired
    private BidRepository bidRepository;

    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public Bid findById(int id) {
        return bidRepository.findOne(id);
    }
}
