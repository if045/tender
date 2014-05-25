package com.softserveinc.tender.service;


import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Tender;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Tender;

import java.util.List;

public interface TenderService {
    List<Tender> findAll();

    Tender findOne(int id);

    Tender findOneWithUnits(int id);

    List<Tender> findByCustomParameters(Double min, Double max, List<Integer> status, List<Integer> categories, List<Integer> locations);

    List<Tender> findByCategory(List<Integer> categories);

}
