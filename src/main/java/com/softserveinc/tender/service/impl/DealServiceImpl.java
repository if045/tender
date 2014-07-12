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

    public static final String PERCENT = "%";
    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private DealStatusService dealStatusService;

    @Override
    public List<Deal> findAllDealsForCustomer(Pageable pageable, Integer id, String tenderTitle) {
        Integer searchFlag = (tenderTitle == null) ? 1 : 0;
        if (searchFlag == 0) {
            tenderTitle = PERCENT + tenderTitle.toLowerCase().trim() + PERCENT;
        }
        return dealRepository.findAllDealsForCustomer(id,pageable, tenderTitle, searchFlag);
    }

    @Override
    public List<Deal> findAllDealsForSeller(Pageable pageable, Integer id, String tenderTitle) {
        Integer searchFlag = (tenderTitle == null) ? 1 : 0;
        if (searchFlag == 0) {
            tenderTitle = PERCENT + tenderTitle.toLowerCase().trim() + PERCENT;
        }
        return dealRepository.findAllDealsForSeller(id,pageable, tenderTitle, searchFlag);
    }

    @Override
    public Long getNewDealsNumberForSeller(Integer id, String tenderTitle) {
        Integer searchFlag = (tenderTitle == null) ? 1 : 0;
        if (searchFlag == 0) {
            tenderTitle = PERCENT + tenderTitle.toLowerCase().trim() + PERCENT;
        }
        return dealRepository.getNewDealsNumberForSeller(id, tenderTitle, searchFlag);
    }

    @Override
    public Long getDealsNumberForCustomer(Integer id, String tenderTitle) {
        Integer searchFlag = (tenderTitle == null) ? 1 : 0;
        if (searchFlag == 0) {
            tenderTitle = PERCENT + tenderTitle.toLowerCase().trim() + PERCENT;
        }
        return dealRepository.getDealsNumberForCustomer(id, tenderTitle, searchFlag);
    }

    @Override
    public Long getDealsNumberForSeller(Integer id, String tenderTitle) {
        Integer searchFlag = (tenderTitle == null) ? 1 : 0;
        if (searchFlag == 0) {
            tenderTitle = PERCENT + tenderTitle.toLowerCase().trim() + PERCENT;
        }
        return dealRepository.getDealsNumberForSeller(id, tenderTitle, searchFlag);
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
