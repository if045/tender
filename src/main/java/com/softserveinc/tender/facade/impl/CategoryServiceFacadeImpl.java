package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.facade.CategoryServiceFacade;
import com.softserveinc.tender.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("categoryServiceFacade")
@Transactional
public class CategoryServiceFacadeImpl implements CategoryServiceFacade{

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<CategoryDto> findCategories() {
        List<Category> categories=categoryService.findAll();
        return mapCategories(categories);
    }

    private CategoryDto mapCategory (Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        if (category.getParent()!=null) {
            categoryDto.setParent(category.getParent().getId());
        }
        return categoryDto;
    }

    private List<CategoryDto> mapCategories(List<Category> categories){
        List<CategoryDto> categoryDtos=new ArrayList<>();
        for(Category category:categories){
            categoryDtos.add(mapCategory(category));
        }
        return categoryDtos;
    }
}
