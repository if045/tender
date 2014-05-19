package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender,Integer>{

    @Query("select t from Tender t where t.status.name = ?1")
    List<Tender> findByStatus(String status);
}
