package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.repo.CategoryRepository;
import com.softserveinc.tender.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllWithCategory() {
        return categoryRepository.findAllWithCategory();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

}
