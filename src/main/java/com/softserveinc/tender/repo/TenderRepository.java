package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TenderRepository extends JpaRepository<Tender, Integer> {

    @Query("select t from Tender t inner join t.locations l , Unit u, Item i " +
            "where t.id = u.tender.id and i.id = u.item.id and i.category.id in (:categories) " +
            "and t.suitablePrice between :min and :max and t.status.id in (:status) " +
            "and l.id IN (:locations)" +
            "and t.endDate between :minDate and :maxDate")
    List<Tender> findByCustomParameters(@Param("min") double min,
                                        @Param("max") double max,
                                        @Param("status") List<Integer> status,
                                        @Param("categories") List<Integer> categories,
                                        @Param("locations") List<Integer> locations,
                                        @Param("minDate") Date minDate,
                                        @Param("maxDate") Date maxDate);

    @Query("select t from Tender t, Unit u, Item i where t.id = u.tender.id and i.id = u.item.id and i.category.id in (:categories)")
    List<Tender> findByCategory(@Param("categories") List<Integer> categories);
}
