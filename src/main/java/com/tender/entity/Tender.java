package com.tenders.entity;

import java.sql.Date;
import java.util.List;

public class Tender {
    private Integer id;
    private User author;
    private String title;
    private Date createDate;
    private Date endDate;
    private TenderStatus status;
    private Double price;
    private String description;
    private List<Item> items;
    private List<Location> locations;
}
