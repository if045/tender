package com.softserveinc.tender.service;


import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.entity.ConflictStatus;

import java.util.List;

public interface ConflictService {

    List<Conflict> findAll();
    Conflict findById(int id);

}
