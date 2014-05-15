package com.softserveinc.tender.entity;

import java.util.List;

public class Item {
    private Integer id;
    private String name;
    private Character type;
    private Category category;
    private List<Bid> bids;
}
