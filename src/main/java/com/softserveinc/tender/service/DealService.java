package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import java.util.List;

public interface DealService {

    List<Deal> findAllDeals();
    Deal findDealById(Integer id);
    void saveDeal(Deal deal);
}
