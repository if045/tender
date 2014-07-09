package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.UnitSaveDto;
import com.softserveinc.tender.facade.TenderServiceFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class TenderServiceFacadeImplTest {

    private static final String TEST_TITLE = "Test Title";
    private static final BigDecimal TEST_SUITABLE_PRICE = new BigDecimal(1000);
    private static final String TEST_END_DATE = "2014/12/03";

    private static final String TEST_UNIT_CATEGORY_ID = "1";
    private static final String TEST_UNIT_ITEM_ID = "2";
    private static final char TEST_UNIT_ITEM_TYPE = 'P';
    private static final String TEST_UNIT_MEASURMENT_ID = "1";
    private static final Double TEST_UNIT_QUANTITY = (double) 1;

    private TenderSaveDto tenderSaveDto;

    @Autowired
    private TenderServiceFacade tenderServiceFacade;

    @Before
    public void setUp() throws Exception {
        tenderSaveDto = new TenderSaveDto();
    }

    @Test
    public void testSaveTender() throws Exception {
        UnitSaveDto unitSaveDto = new UnitSaveDto();
        unitSaveDto.setCategory(TEST_UNIT_CATEGORY_ID);
        unitSaveDto.setItem(TEST_UNIT_ITEM_ID);
        unitSaveDto.setItemType(TEST_UNIT_ITEM_TYPE);
        unitSaveDto.setMeasurment(TEST_UNIT_MEASURMENT_ID);
        unitSaveDto.setQuantity(TEST_UNIT_QUANTITY);
        List<UnitSaveDto> unitSaveDtos = new ArrayList<>();
        unitSaveDtos.add(unitSaveDto);

        tenderSaveDto.setTitle(TEST_TITLE);
        tenderSaveDto.setSuitablePrice(TEST_SUITABLE_PRICE);
        tenderSaveDto.setEndDate(TEST_END_DATE);
        tenderSaveDto.setUnits(unitSaveDtos);

        TenderDto tenderDto = tenderServiceFacade.saveTender(tenderSaveDto);

        assertEquals(TEST_TITLE, tenderDto.getTitle());
        assertEquals(TEST_SUITABLE_PRICE, tenderDto.getSuitablePrice());
    }
}