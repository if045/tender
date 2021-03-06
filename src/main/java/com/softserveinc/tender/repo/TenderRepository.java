package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Tender;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TenderRepository extends JpaRepository<Tender, Integer> {

    @Query("select distinct t from Tender t inner join t.locations l, Category c, Unit u, Item i, Proposal p " +
            "where t.id = u.tender.id and c.id=i.category.id and i.id = u.item.id " +
            "and (1 = :searchFlag or t.title like :searchParam) "+
            "and (1 = :priceFlag or t.suitablePrice between :min and :max) " +
            "and (1 = :categoryFlag or i.category.id in (:categories)) " +
            "and (1 = :statusFlag or t.status.id in (:status)) " +
            "and (0 = :statusFlag or t.status.active = true) " +
            "and (1 = :locationFlag or l.id IN (:locations)) " +
            "and (1 = :itemFlag or i.id IN (:items)) " +
            "and t.endDate between :minDate and :maxDate " +
            "and (1 = :customerTendersFlag or t.author.id = :profileId) " +
            "and (1 = :sellerTendersFlag or (p.seller.id = :profileId and p.tender.id = t.id))")
    List<Tender> findByCustomParameters(@Param("searchParam")String tenderTitle,
                                        @Param("min") BigDecimal min,
                                        @Param("max") BigDecimal max,
                                        @Param("status") List<Integer> status,
                                        @Param("categories") Set<Integer> categories,
                                        @Param("locations") List<Integer> locations,
                                        @Param("items") List<Integer> items,
                                        @Param("minDate") Date minDate,
                                        @Param("maxDate") Date maxDate,
                                        @Param("categoryFlag") Integer categoryFlag,
                                        @Param("itemFlag") Integer itemFlag,
                                        @Param("locationFlag") Integer locationFlag,
                                        @Param("statusFlag") Integer statusFlag,
                                        @Param("priceFlag") Integer priceFlag,
                                        @Param("searchFlag") Integer searchFlag,
                                        @Param("customerTendersFlag") Integer customerTendersFlag,
                                        @Param("sellerTendersFlag") Integer sellerTendersFlag,
                                        @Param("profileId") Integer profileId,
                                        Pageable pageable);

    @Query("select count(distinct t) from Tender t inner join t.locations l, Category c, Unit u, Item i, Proposal p " +
            "where t.id = u.tender.id and c.id=i.category.id and i.id = u.item.id " +
            "and (1 = :searchFlag or t.title like :searchParam) "+
            "and (1 = :priceFlag or t.suitablePrice between :min and :max) " +
            "and (1 = :categoryFlag or i.category.id in (:categories)) " +
            "and (1 = :statusFlag or t.status.id in (:status)) " +
            "and (0 = :statusFlag or t.status.active = true) " +
            "and (1 = :locationFlag or l.id IN (:locations)) " +
            "and (1 = :itemFlag or i.id IN (:items)) " +
            "and t.endDate between :minDate and :maxDate " +
            "and (1 = :customerTendersFlag or t.author.id = :profileId) " +
            "and (1 = :sellerTendersFlag or (p.seller.id = :profileId and p.tender.id = t.id))")
    Long getTendersNumber(@Param("searchParam")String tenderTitle,
                          @Param("min") BigDecimal min,
                          @Param("max") BigDecimal max,
                          @Param("status") List<Integer> status,
                          @Param("categories") Set<Integer> categories,
                          @Param("locations") List<Integer> locations,
                          @Param("items") List<Integer> items,
                          @Param("minDate") Date minDate,
                          @Param("maxDate") Date maxDate,
                          @Param("categoryFlag") Integer categoryFlag,
                          @Param("itemFlag") Integer itemFlag,
                          @Param("locationFlag") Integer locationFlag,
                          @Param("statusFlag") Integer statusFlag,
                          @Param("priceFlag") Integer priceFlag,
                          @Param("searchFlag") Integer searchFlag,
                          @Param("customerTendersFlag") Integer customerTendersFlag,
                          @Param("sellerTendersFlag") Integer sellerTendersFlag,
                          @Param("profileId") Integer profileId);

}
