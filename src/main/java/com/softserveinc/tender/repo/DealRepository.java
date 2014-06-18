package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DealRepository extends JpaRepository<Deal, Integer> {
    List<Deal> findByProposalId(Integer proposalId);

    @Query("select d from Deal d inner join d.bid b where b.unit.id = :unitId")
    List<Deal> findByUnitId(@Param("unitId")Integer unitId);

    List<Deal> findBySellerId(Integer id);
}
