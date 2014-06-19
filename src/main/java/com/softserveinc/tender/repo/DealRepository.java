package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.Tender;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    @Query("select distinct d from Deal d where d.customer.id = :customerId")
    List<Deal> findAllDealsForCustomer(@Param("customerId") Integer id,Pageable pageable);

    @Query("select distinct d from Deal d where d.seller.id = :sellerId")
    List<Deal> findAllDealsForSeller(@Param("sellerId") Integer id,Pageable pageable);

    @Query("select count(distinct d) from Deal d where d.seller.id = :sellerId and d.status.id IN (1,2)")
    Long getNewDealsNumberForSeller(@Param("sellerId") Integer id);

    @Query("select count(distinct d) from Deal d")
    Long getDealsNumber();

    List<Deal> findByProposalId(Integer proposalId);

    @Query("select d from Deal d inner join d.bid b where b.unit.id = :unitId")
    List<Deal> findByUnitId(@Param("unitId")Integer unitId);

    List<Deal> findBySellerId(Integer id);
}
