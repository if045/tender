package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Integer>{
}
