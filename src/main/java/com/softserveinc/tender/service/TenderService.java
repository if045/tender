package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Tender;

import java.sql.Date;
import java.util.List;

public interface TenderService {
    List<Tender> findAll();
    Tender findOne(int id);
    Tender findOneWithUnits(int id);
    List<Tender> findByCustomParameters(Double min, Double max, List<Integer> status, List<Integer> categories, List<Integer> locations, Date minDate, Date maxDate);
    List<Tender> findByCategory(List<Integer> categories);
}
