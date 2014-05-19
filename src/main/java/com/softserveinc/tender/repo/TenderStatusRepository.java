package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.TenderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderStatusRepository extends JpaRepository<TenderStatus, Integer> {
}
