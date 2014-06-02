package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer>{
    Item findByName(String name);

    @Query("select distinct i from Item i where i.id in (SELECT u.item.id FROM Unit u) " +
            "and (1=:categoryFlag or i.category.id in (:categories))")
    List<Item> findAllItemsByTenders(@Param("categories") List<Integer> categories,
                                     @Param("categoryFlag") Integer categoryFlag);

    List<Item> findByCategoryId(Integer id);

    List<Item> findByCategoryIdAndType(Integer id, Character type);
}
