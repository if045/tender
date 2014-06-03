package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.entity.Proposal;

import java.math.BigDecimal;

public class ProposalDto {

    private Integer id;
    private String fullName;
    private String totalBidsPrice;
    private Integer numberOfBids;

    public String countTotalBidsPrice(Proposal proposal) {
        BigDecimal totalBidsSum = new BigDecimal(0);
        for (Bid bid : proposal.getBids()) {
            totalBidsSum = totalBidsSum.add(bid.getPrice());
        }
        return totalBidsSum.toString();
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

    public String getTotalBidsPrice() {
        return totalBidsPrice;
    }

    public void setTotalBidsPrice(String totalBidsPrice) {
        this.totalBidsPrice = totalBidsPrice;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }
}
