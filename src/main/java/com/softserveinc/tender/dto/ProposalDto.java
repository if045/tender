package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.entity.Proposal;

public class ProposalDto {

    private Integer id;
    private String fullName;
    private Double totalPrice;
    private Integer numberOfBids;

    private Double countTotalPrice(Proposal proposal) {
        Double sum = 0.0;
        final int MAX_PERCENTAGE_VALUE = 100;

        for (Bid bid : proposal.getBids()) {
            sum += bid.getPrice();
        }

        if(proposal.getDiscountPercentage() != null) {
            return sum - (sum * (proposal.getDiscountPercentage() / MAX_PERCENTAGE_VALUE));
        } else {
            return sum - proposal.getDiscountCurrency();
        }
    }

    private String convertIntoFullName(Proposal proposal) {
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

    public void setFullName(Proposal proposal) {
        fullName = convertIntoFullName(proposal);
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Proposal proposal) {
        totalPrice = countTotalPrice(proposal);
    }
}
