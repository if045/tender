package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.repo.TenderStatusRepository;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TenderStatusServiceImpl implements TenderStatusService{

    @Autowired
    private TenderStatusRepository tenderStatusRepository;

    @Override
    public List<TenderStatus> findAllTendersStatuses() {
        return tenderStatusRepository.findAllTenderStatuses();
    }

    @Override
    public TenderStatus findTenderStatusById(Integer id) {
        return tenderStatusRepository.findOne(id);
    }

    @Override
    public TenderStatus findTenderStatusByName(String name) {
        return tenderStatusRepository.findByName(name);
    }
}
