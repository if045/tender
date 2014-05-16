package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Integer> {
}
