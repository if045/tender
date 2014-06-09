package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.repo.TenderRepository;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private TenderStatusService tenderStatusService;

    public Tender findOne(int id) {
        return tenderRepository.findOne(id);
    }

    @Override
    public List<Tender> findByCustomParameters(TenderFilter tenderFilter, Pageable pageable) {
        return tenderRepository.findByCustomParameters(tenderFilter.getMinPrice(), tenderFilter.getMaxPrice(),
                                                       tenderFilter.getStatuses(), tenderFilter.getCategories(),
                                                       tenderFilter.getLocations(), tenderFilter.getItems(),
                                                       tenderFilter.getMinDate(), tenderFilter.getMaxDate(),
                                                       tenderFilter.getCategoryFlag(), tenderFilter.getItemFlag(),
                                                       tenderFilter.getLocationFlag(), tenderFilter.getStatusFlag(),
                                                       tenderFilter.getPriceFlag(),
                                                       pageable);
    }

    @Override
    public void updateTenderWithStatus(Integer tenderId, String statusName) {
        Tender tender = tenderRepository.findOne(tenderId);
        TenderStatus tenderStatus = tenderStatusService.findByName(statusName);

        tender.setStatus(tenderStatus);
        tenderRepository.save(tender);
    }

    @Override
    public Tender save(Tender tender) {
        return tenderRepository.saveAndFlush(tender);
    }
}
