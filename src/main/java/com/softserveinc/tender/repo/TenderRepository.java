package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender,Integer>{

    @Query("select t from Tender t where t.status.id = :tender_status")
    List<Tender> findTendersByParameters(@Param("tender_status") int tender_status);
}
