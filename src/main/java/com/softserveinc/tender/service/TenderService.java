package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.repo.TenderFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TenderService {

    Tender findOne(int id);
    List<Tender> findByCustomParameters(TenderFilter tenderFilter, Pageable pageable);
    Long findByCustomParametersResultSize(TenderFilter tenderFilter);
    Tender save(Tender tender);
    void updateTenderWithStatus(Integer tenderId, String statusName);
}
