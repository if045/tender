package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

@Service("tenderServiceFacade")
public class TenderServiceFacadeImpl  implements TenderServiceFacade{

    @Autowired
    private TenderStatusService tenderStatusService;

    @Autowired
    private ModelMapper modelMapper;

    public List<TenderStatusDto> findTenderStatuses() {
        List<TenderStatusDto> statusesDto = new ArrayList<>();
        for(TenderStatus tenderStatus: tenderStatusService.findAll()) {
            statusesDto.add(modelMapper.map(tenderStatus, TenderStatusDto.class));
        }
        return statusesDto;
    }
}