package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderRepository extends JpaRepository<Tender,Integer>{
}
