package com.softserveinc.tender.repo;


import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender, Integer> {
    @Query("select t from Tender t where t.suitablePrice between :min and :max")
    List<Tender> findByCustomParameters(@Param("min") double min,
                                        @Param("max") double max);

    @Query("select t from Tender t, Unit u, Item i where t.id = u.tender.id and i.id = u.item.id and i.category.id in (:categories)")
    List<Tender> findByCategory(@Param("categories") List<Integer> categories);
}
