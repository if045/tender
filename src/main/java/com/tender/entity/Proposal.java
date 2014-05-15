package com.tenders.entity;

import java.util.List;

public class Proposal {
    private Integer id;
    private User seller;
    private Tender tender;
    private Double discountPercentage;
    private Double discountCurrency;
    private String description;
    private List<Bid> bids;
}
