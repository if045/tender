package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.repo.TenderFilter;
import java.util.List;

public interface TenderService {
    List<Tender> findAll();
    Tender findOne(int id);
    List<Tender> findByCustomParameters(TenderFilter tenderFilter);
    List<Tender> findByCategory(List<Integer> categories);
}
