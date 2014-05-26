package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import java.util.List;

public interface TenderServiceFacade {
    public List<TenderStatusDto> findTenderStatuses();
    public List<ItemDto> findItems();
    public List<LocationDto> findLocations();
}
