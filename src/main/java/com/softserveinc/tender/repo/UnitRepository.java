package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findUnitsByTenderId(int tenderId);
}
