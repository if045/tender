package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.CheckedStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckedStatusRepository extends JpaRepository<CheckedStatus, Integer> {
}
