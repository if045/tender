package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.repo.TenderRepository;
import com.softserveinc.tender.repo.UnitRepository;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<Tender> findByCustomParameters(Double min, Double max, List<Integer> status, List<Integer> categories, List<Integer> locations) {
        return tenderRepository.findByCustomParameters(min, max, status, categories, locations);
    }

    @Override
    public List<Tender> findByCategory(List<Integer> categories) {
        return tenderRepository.findByCategory(categories);
    }
}
