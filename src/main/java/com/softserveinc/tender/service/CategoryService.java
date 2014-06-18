package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Category;
import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findCategoryById(Integer id);
    void saveCategory(Category category);
    List<Category> findAllWithCategory();
    Category findByName(String name);
}
