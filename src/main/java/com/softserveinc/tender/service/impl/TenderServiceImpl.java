package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.repo.TenderRepository;
import com.softserveinc.tender.repo.UnitRepository;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private UnitRepository unitRepository;

    public Tender findOne(int id) {
        return tenderRepository.findOne(id);
    }


    @Transactional
    public List<Tender> findAll() {
        return tenderRepository.findAll();
    }

    @Transactional
    public Tender findOneWithUnits(int id) {
        Tender tender = new Tender();


        //дописати

        return tender;
    }

    @Override
    public List<Tender> findByCustomParameters(Double min, Double max, List<Integer> status, List<Integer> categories, List<Integer> locations, Date minDate, Date maxDate) {
        return tenderRepository.findByCustomParameters(min, max, status, categories, locations, minDate, maxDate);
    }

    @Override
    public List<Tender> findByCategory(List<Integer> categories) {
        return tenderRepository.findByCategory(categories);
    }

}
