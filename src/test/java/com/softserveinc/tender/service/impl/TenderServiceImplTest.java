package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.TenderStatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/TenderServiceImplTest-context.xml")
@Transactional
public class TenderServiceImplTest {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TenderStatusService tenderStatusService;

    @Test
    public void testFindAll() throws Exception {
        assertEquals(34, tenderService.findAll().size());
    }

    @Test
    public void testSave() throws Exception {
        Tender tender = new Tender();

        tender.setAuthor(profileService.findProfileByUserLogin("odin_ogame@ukr.net"));
        tender.setTitle("Test Title");
        tender.setCreateDate(new Date());
        tender.setEndDate(new Date());
        tender.setStatus(tenderStatusService.findByName("Open"));
        tender.setSuitablePrice(new BigDecimal(10));
        tenderService.save(tender);

        assertEquals(35, tenderService.findAll().size());
        assertEquals("Test Title", tenderService.findOne(tender.getId()).getTitle());
    }
}
