package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.repo.TenderFilter;

import java.util.List;

public interface ItemServiceFacade {
    List<ItemDto> findItems(TenderFilter tenderFilter);
}
