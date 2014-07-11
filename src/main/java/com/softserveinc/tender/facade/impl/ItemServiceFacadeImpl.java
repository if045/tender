package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.facade.ItemServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.util.Convertible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("itemServiceFacade")
@Transactional
public class ItemServiceFacadeImpl implements ItemServiceFacade{

    @Autowired
    private Convertible convertible;

    @Autowired
    private ItemService itemService;

    @Override
    public List<ItemDto> findItemsByCategoryAndType(TenderFilter tenderFilter) {
        return convertible.mapObjects(itemService.findItemsByCategoryAndType(tenderFilter), ItemDto.class);
    }
}
