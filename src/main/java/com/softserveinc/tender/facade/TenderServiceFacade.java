package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.repo.TenderFilter;
import java.util.List;

public interface TenderServiceFacade {

    List<TenderStatusDto> findTendersStatuses();
    List<ItemDto> findTendersItems(TenderFilter tenderFilter);
    List<TenderDto> findByCustomParams(TenderFilter tenderFilter);
    List<LocationDto> findTendersLocations();
    List<CategoryDto> findTendersCategories();
    List<UnitDto> findUnitsByTenderId(Integer tenderId);
}
