package com.softserveinc.tender.service.impl;

<<<<<<< HEAD
import com.softserveinc.tender.entity.Location;
=======
import com.softserveinc.tender.entity.Category;
>>>>>>> develop
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.repo.TenderRepository;
import com.softserveinc.tender.repo.UnitRepository;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.management.counter.Units;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
=======
>>>>>>> develop
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
<<<<<<< HEAD
    public List<Tender> findByCustomParameters(Double min, Double max, Integer[] status,List<Integer> locations) {
        return tenderRepository.findByCustomParameters(min, max, status,locations);
=======
    public List<Tender> findByCustomParameters(Double min, Double max, List<Integer> status, List<Integer> categories) {
        return tenderRepository.findByCustomParameters(min, max, status, categories);
    }

    @Override
    public List<Tender> findByCategory(List<Integer> categories) {
        return tenderRepository.findByCategory(categories);
>>>>>>> develop
    }
}
