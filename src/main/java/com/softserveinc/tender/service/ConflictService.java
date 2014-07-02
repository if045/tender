package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Conflict;
import java.util.List;

public interface ConflictService {

    List<Conflict> findAllByModeratorId(Integer id);
    Conflict findById(int id);
    Conflict save(Conflict conflict);


}
