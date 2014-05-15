package com.softserveinc.tender.entity;

import java.sql.Date;

public class Comment {
    private Integer id;
    private Tender tender;
    private User user;
    private String message;
    private Date date;
}
