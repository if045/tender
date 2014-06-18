package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import java.util.List;

public interface DealService {

    List<Deal> findAllDeals();
    List<Deal> findAllDealsBySeller(Integer id);
    Deal findDealById(Integer id);
    Deal saveDeal(Deal deal);
    void updateDealWithStatus(Integer dealId, String statusName);
    List<Deal> findByProposalId(Integer proposalId);
    List<Deal> findByUnitId(Integer unitId);
}
