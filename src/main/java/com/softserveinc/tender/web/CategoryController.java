package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.facade.CategoryServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceFacade categoryServiceFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody List<CategoryDto> findCategories() {
        return categoryServiceFacade.findCategories();
    }
}
