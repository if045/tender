package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Conflict;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConflictService {

    List<Conflict> findAllByModeratorId(Integer id, Pageable pageable, String searchParam);

    Conflict findById(int id);

    Conflict save(Conflict conflict);

    Long getConflictsNumber(Integer id, String searchParam);
}
