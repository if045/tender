package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Bid;
import java.util.List;

public interface BidService {

    List<Bid> findAll();
    Bid findById(int id);
    Bid save(Bid bid);
}
