package com.tenders.entity;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    private Profile profile;	
    private String password;
    private Date createDate;
    private String login;
    private List<Role> roles;
    private List<Location> sellerLocations;
    private List<Category> sellerCategories;
    private List<Category> moderatorCategories;
}
