package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.TenderStatus;

import java.util.List;

public interface TenderStatusService {

    List<TenderStatus> findAllTendersStatuses();
    TenderStatus findTenderStatusById(Integer id);
}
