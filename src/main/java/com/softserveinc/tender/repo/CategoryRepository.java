package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select distinct c from Category c where c.id in (SELECT i.category.id from Item i where i.id in (SELECT u.item.id FROM Unit u))")
    List<Category> findCategoriesWithTenders();
}
