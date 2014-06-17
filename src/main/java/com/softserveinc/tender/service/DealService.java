package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DealService {

    List<Deal> findAllDeals(Pageable pageable);
    Long getDealsNumber();
    Deal findDealById(Integer id);
    void saveDeal(Deal deal);
    void updateDealWithStatus(Integer dealId, String statusName);
}
