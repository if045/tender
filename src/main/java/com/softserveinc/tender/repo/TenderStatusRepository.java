package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.TenderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenderStatusRepository extends JpaRepository<TenderStatus, Integer> {

    @Query("select distinct s from Tender t inner join t.status s order by s.id asc")
    List<TenderStatus> getAllTenderStatuses();

    TenderStatus findByName(String name);
}
