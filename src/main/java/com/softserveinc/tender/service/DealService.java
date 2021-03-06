package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Deal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealService {

    List<Deal> findAllDealsForCustomer(Pageable pageable,Integer id, String tenderTitle);
    List<Deal> findAllDealsForSeller(Pageable pageable,Integer id, String tenderTitle);
    Long getDealsNumberForCustomer(Integer id, String tenderTitle);
    Long getDealsNumberForSeller(Integer id, String tenderTitle);
    Long getNewDealsNumberForSeller(Integer id, String tenderTitle);
    Deal findDealById(Integer id);
    Deal saveDeal(Deal deal);
    void updateDealWithStatus(Integer dealId, String statusName);
    List<Deal> findByProposalId(Integer proposalId);
    List<Deal> findByUnitId(Integer unitId);
}
