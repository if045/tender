package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.ConflictStatus;
import java.util.List;

public interface ConflictStatusService {

    List<ConflictStatus> findAll();
    ConflictStatus findById(int id);
}
