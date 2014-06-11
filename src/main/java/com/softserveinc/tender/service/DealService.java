package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import java.util.List;

public interface DealService {

    List<Deal> findAllDeals();
    Deal findDealById(Integer id);
    Deal saveDeal(Deal deal);
    void updateDealWithStatus(Integer dealId, String statusName);
}
