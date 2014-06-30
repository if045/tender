package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.CheckedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface CheckedStatusRepository extends JpaRepository<CheckedStatus, Integer> {
    @Query("select c from CheckedStatus c where c.name = :statusName")
    CheckedStatus findByName(@Param("statusName")String statusName);
}
