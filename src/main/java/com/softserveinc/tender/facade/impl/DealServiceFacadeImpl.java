package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.service.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("dealServiceFacade")
@Transactional
public class DealServiceFacadeImpl implements DealServiceFacade {

    @Autowired
    private DealService dealService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DealDto> findAllDeals() {
        List<Deal> deals = dealService.findAllDeals();
        return mapDeals(deals);
    }

    @Override
    public void updateDealWithStatus(Integer dealId, String statusName) {
        dealService.updateDealWithStatus(dealId, statusName);
    }

    private List<DealDto> mapDeals(List<Deal> deals) {
        List<DealDto> dealDtos = new ArrayList<>();
        for (Deal deal : deals) {
            dealDtos.add(mapDeal(deal));
        }

        return dealDtos;
    }

    private DealDto mapDeal(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setDate(deal.getDate());
        dealDto.setStatus(deal.getStatus().getName());
        dealDto.setSum(deal.getSum());
        dealDto.setDate(deal.getDate());
        dealDto.setTitle(deal.getProposal().getTender().getTitle());
        dealDto.setBusinessPartner(deal.getCustomer().getFirstName());

        return dealDto;
    }

}
