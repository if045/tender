package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.facade.CategoryServiceFacade;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.util.UtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryServiceFacade")
@Transactional
public class CategoryServiceFacadeImpl implements CategoryServiceFacade{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public List<CategoryDto> findCategories() {
        return utilMapper.mapObjects(categoryService.findAll(), CategoryDto.class);
    }
}
