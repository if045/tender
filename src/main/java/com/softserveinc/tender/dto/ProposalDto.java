package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.entity.Proposal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ProposalDto {

    private Integer id;
    private String fullName;
    private Double totalBidsPrice;
    private Integer numberOfBids;
    private List<BidDto> bidDtos;

    public Double countTotalBidsPrice(Proposal proposal) {
        final Integer START_COUNT_VALUE = 0;

        BigDecimal totalBidsSum = new BigDecimal(START_COUNT_VALUE);
        for (Bid bid : proposal.getBids()) {
            totalBidsSum = totalBidsSum.add(bid.getPrice());
        }
        return totalBidsSum.doubleValue();
    }

    public String convertIntoFullName(Proposal proposal) {
        return proposal.getSeller().getProfile().getFirstName() + " " +
                proposal.getSeller().getProfile().getLastName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getTotalBidsPrice() {
        return totalBidsPrice;
    }

    public void setTotalBidsPrice(Double totalBidsPrice) {
        this.totalBidsPrice = totalBidsPrice;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public List<BidDto> getBidDtos() {
        return bidDtos;
    }

    public void setBidDtos(List<BidDto> bidDtos) {
        this.bidDtos = bidDtos;
    }
}
