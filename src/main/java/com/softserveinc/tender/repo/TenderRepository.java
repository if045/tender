package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender,Integer> {
    @Query("SELECT b FROM Tender b where b.location.id = :id")
    public List<Location> find(@Param("id") int id);
}