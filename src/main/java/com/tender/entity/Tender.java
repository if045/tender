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
    private Double suitablePrice;
    private String description;
    private List<Unit> units;
    private List<Location> locations;
}
