package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CategoryDto;

import java.util.List;

public interface CategoryServiceFacade {
    List<CategoryDto> findCategories();
}
