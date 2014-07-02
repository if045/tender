package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.facade.impl.TenderServiceFacadeImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TenderServiceFacadeImplTest {

    private TenderServiceFacadeImpl tenderServiceFacade;

    @Before
    public void setUp() throws Exception {
        tenderServiceFacade = new TenderServiceFacadeImpl();
    }

    @Test
    public void testMapCategory() throws Exception {
        Category category = new Category();
        Category parent = new Category();

        parent.setId(26);

        category.setId(15);
        category.setName("test category name");
        category.setParent(parent);

        CategoryDto categoryDto = tenderServiceFacade.mapCategory(category);

        assertEquals(new Integer(15), categoryDto.getId());
        assertEquals("test category name", categoryDto.getName());
        assertEquals(new Integer(26), categoryDto.getParentId());
    }
}
