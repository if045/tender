package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.DealStatus;
import java.util.List;

public interface DealStatusService {

    List<DealStatus> findAllDealStatuses();
    DealStatus findDealStatusById(Integer id);
    DealStatus findByName(String name);
}
