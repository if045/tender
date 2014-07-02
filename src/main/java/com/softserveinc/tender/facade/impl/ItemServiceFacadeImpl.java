package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.facade.ItemServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service("itemServiceFacade")
@Transactional
public class ItemServiceFacadeImpl implements ItemServiceFacade{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @Override
    public List<ItemDto> findItemsByCategoryAndType(TenderFilter tenderFilter) {
        Type targetListType = new TypeToken<List<ItemDto>>(){}.getType();
        return modelMapper.map(itemService.findItemsByCategoryAndType(tenderFilter), targetListType);
    }
}
