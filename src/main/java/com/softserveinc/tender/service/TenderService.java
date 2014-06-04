package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.repo.TenderFilter;
import java.util.List;

public interface TenderService {

    Tender findOne(Integer id);
    List<Tender> findByCustomParameters(TenderFilter tenderFilter);
}
