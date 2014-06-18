package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DealService {

    List<Deal> findAllDealsForCustomer(Pageable pageable,Integer id);
    List<Deal> findAllDealsForSeller(Pageable pageable,Integer id);
    Long getDealsNumber();
    Deal findDealById(Integer id);
    Deal saveDeal(Deal deal);
    void updateDealWithStatus(Integer dealId, String statusName);
    List<Deal> findByProposalId(Integer proposalId);
    List<Deal> findByUnitId(Integer unitId);
}
