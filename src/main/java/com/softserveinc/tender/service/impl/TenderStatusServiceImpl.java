package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.repo.TenderStatusRepository;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TenderStatusServiceImpl implements TenderStatusService{

    @Autowired
    private TenderStatusRepository tenderStatusRepository;

    @Override
    public List<TenderStatus> findAllTenderStatuses() {
        return tenderStatusRepository.findAll(sortTenderStatusesByIdAsc());
    }

    private Sort sortTenderStatusesByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    @Override
    public TenderStatus findTenderStatusById(Integer id) {
        return tenderStatusRepository.findOne(id);
    }
}
