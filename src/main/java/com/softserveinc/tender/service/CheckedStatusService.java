package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.CheckedStatus;
import java.util.List;

public interface CheckedStatusService {

    List<CheckedStatus> findAll();
    CheckedStatus findById(int id);
    CheckedStatus findByName(String status);
}
