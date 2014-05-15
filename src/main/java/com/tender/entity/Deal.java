package com.tenders.entity;

import java.sql.Date;
import java.util.List;

public class Deal {
    private Integer id;
    private Bid bid;
    private Profile customer;
    private Profile seller;
    private Double sum;
    private Date date;
    private DealStatus status;
    private Proposal proposal;
}
