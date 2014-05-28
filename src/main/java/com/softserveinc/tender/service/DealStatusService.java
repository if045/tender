package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.DealStatus;
import java.util.List;

public interface DealStatusService {

    List<DealStatus> findAll();
    DealStatus findDealStatusById(int id);
}
