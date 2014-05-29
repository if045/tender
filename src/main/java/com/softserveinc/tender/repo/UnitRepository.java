package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UnitRepository extends JpaRepository<Unit,Integer>{
    @Query("select u from Unit u where u.tender.id = :id ")
     List<Unit> findUnits(@Param("id")int id);
}
