package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.service.TenderStatusService;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TenderFacade {

    public TenderStatusDto map(TenderStatus tenderStatus) {

        TenderStatusDto tenderStatusDto = new TenderStatusDto();

        tenderStatusDto.setId(tenderStatus.getId());
        tenderStatusDto.setName(tenderStatus.getName());

        return tenderStatusDto;
    }
}
