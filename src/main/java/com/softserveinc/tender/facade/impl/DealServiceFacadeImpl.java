package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("dealServiceFacade")
@Transactional
public class DealServiceFacadeImpl implements DealServiceFacade {

    @Autowired
    private DealService dealService;

    @Override
    public List<DealDto> findAllDeals() {
        List<Deal> deals = dealService.findAllDeals();
        return mapDeals(deals);
    }

    @Override
    public void updateDealWithStatus(Integer dealId, String statusName) {
        dealService.updateDealWithStatus(dealId, statusName);
    }

    @Override
    public List<DealDto> mapDeals(List<Deal> deals) {
        List<DealDto> dealDtos = new ArrayList<>();
        for (Deal deal : deals) {
            dealDtos.add(mapDeal(deal));
        }

        return dealDtos;
    }

    @Override
    public DealDto mapDeal(Deal deal) {
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
