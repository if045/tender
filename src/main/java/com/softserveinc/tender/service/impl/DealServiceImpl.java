package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.DealStatus;
import com.softserveinc.tender.repo.DealRepository;
import com.softserveinc.tender.service.DealService;
import com.softserveinc.tender.service.DealStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private DealStatusService dealStatusService;

    @Override
    public List<Deal> findAllDealsForCustomer(Pageable pageable, Integer id, String tenderTitle) {
        Integer searchFlag;
        if (tenderTitle==null){
            searchFlag=1;
        }else {
            searchFlag=0;
            tenderTitle = "%"+tenderTitle+"%";
        }
        return dealRepository.findAllDealsForCustomer(id,pageable, tenderTitle, searchFlag);
    }

    @Override
    public List<Deal> findAllDealsForSeller(Pageable pageable, Integer id, String tenderTitle) {
        Integer searchFlag;
        if (tenderTitle==null){
            searchFlag=1;
        }else {
            searchFlag=0;
            tenderTitle = "%"+tenderTitle+"%";
        }
        return dealRepository.findAllDealsForSeller(id,pageable, tenderTitle, searchFlag);
    }
    @Override
    public Long getDealsNumber() {
        return dealRepository.getDealsNumber();
    }

    @Override
    public Deal findDealById(Integer id) {
        return dealRepository.findOne(id);
    }

    @Override
    public Deal saveDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public void updateDealWithStatus(Integer dealId, String statusName) {
        Deal deal = dealRepository.findOne(dealId);
        DealStatus dealStatus = dealStatusService.findByName(statusName);
        deal.setStatus(dealStatus);
        dealRepository.save(deal);
    }

    public List<Deal> findByProposalId(Integer proposalId) {
        return dealRepository.findByProposalId(proposalId);
    }


    public List<Deal> findByUnitId(Integer unitId) {
        return dealRepository.findByUnitId(unitId);

    }
}
