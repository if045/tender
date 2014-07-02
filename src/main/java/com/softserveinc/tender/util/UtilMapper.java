package com.softserveinc.tender.util;

import com.softserveinc.tender.dto.BidDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.service.DealService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public class UtilMapper extends ModelMapper{
    @Autowired
    private DealService dealService;

    public UtilMapper() {
        //Mapping for Bid into BidDto
        this.addMappings(new PropertyMap<Bid, BidDto>() {
            @Override
            protected void configure() {
                map().setBidId(source.getId());
            }
        });

        //Mapping for Proposal into ProposalDto
        this.addMappings(new PropertyMap<Proposal, ProposalDto>() {
            Converter<List<Bid>, Double> toTotalBidPrice = new AbstractConverter<List<Bid>, Double>() {
                @Override
                protected Double convert(List<Bid> source) {
                    final Integer START_COUNT_VALUE = 0;
                    BigDecimal totalBidsSum = new BigDecimal(START_COUNT_VALUE);
                    for (Bid bid : source) {
                        totalBidsSum = totalBidsSum.add(bid.getPrice());
                    }

                    return totalBidsSum.doubleValue();
                }
            };

            Converter<Integer, Boolean> toHaveDeals = new AbstractConverter<Integer, Boolean>() {
                @Override
                protected Boolean convert(Integer source) {
                    return dealService.findByProposalId(source).size() > 0;
                }
            };

            @Override
            protected void configure() {
                map().setFullName(source.getSeller().getProfile().getFirstName());
                map().setNumberOfBids(source.getBids().size());
                using(toTotalBidPrice).map(source.getBids()).setTotalBidsPrice(null);
                using(toHaveDeals).map(source.getId()).setHaveDeals(null);
            }
        });
    }

    public static Object mapObject(Object source, Object destination){return null;}
    public static List<Object> mapObjects(List<Object> source, Object destination){return null;}
}
