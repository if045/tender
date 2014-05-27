package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.repo.TenderRepository;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    public Tender findOne(int id) {
        return tenderRepository.findOne(id);
    }

    public List<Tender> findAll() {
        return tenderRepository.findAll();
    }

    @Override
    public List<Tender> findByCustomParameters(TenderFilter tenderFilter) {
        return tenderRepository.findByCustomParameters(tenderFilter.getMinPrice(), tenderFilter.getMaxPrice(), tenderFilter.getStatuses(), tenderFilter.getCategories(), tenderFilter.getLocations(), tenderFilter.getItems(), tenderFilter.getMinDate(), tenderFilter.getMaxDate(), tenderFilter.getCategoryFlag(), tenderFilter.getItemFlag(), tenderFilter.getLocationFlag(), tenderFilter.getStatusFlag());
    }

    @Override
    public List<Tender> findByCategory(List<Integer> categories) {
        return tenderRepository.findByCategory(categories);
    }
}
