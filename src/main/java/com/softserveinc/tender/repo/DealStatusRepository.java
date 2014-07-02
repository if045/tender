package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealStatusRepository extends JpaRepository<DealStatus, Integer>{
    DealStatus findByName(String name);
}
