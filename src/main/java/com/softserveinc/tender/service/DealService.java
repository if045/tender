package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import java.util.List;

public interface DealService {
    List<Deal> findAll();
    Deal findDealById(int id);
    void saveDeal(Deal deal);
}
