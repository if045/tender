package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item,Integer>{

    @Query("select distinct i from Item i where i.id in (SELECT u.item.id FROM Unit u) " +
            "and (1=:categoryFlag or i.category.id in (:categories))")
    List<Item> findAllItemsByTenders(@Param("categories") Set<Integer> categories,
                                     @Param("categoryFlag") Integer categoryFlag);

    @Query("select distinct i from Item i " +
            "where (1=:categoryFlag or i.category.id = (SELECT c.id FROM Category c where c.id=:category))"+
            "and (1=:typeFlag or i.type=:type)")
    List<Item> findItems(@Param("category") Integer category,
                         @Param("categoryFlag") Integer categoryFlag,
                         @Param("type") Character type,
                         @Param("typeFlag") Integer typeFlag);

    Item findByName(String name);

    @Query("select distinct i from Item i where i.category.id=:categoryId and i.name=:name")
    Item findByNameAndCategoryId(@Param("name") String name,@Param("categoryId") Integer categoryId);
}
