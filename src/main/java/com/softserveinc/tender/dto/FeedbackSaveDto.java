package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.User;

public class FeedbackSaveDto {
    private Integer id;
    private User user;
    private Profile profile;
    private Integer communication;
    private Integer speed;
    private Integer logistic;
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getLogistic() {
        return logistic;
    }

    public void setLogistic(Integer logistic) {
        this.logistic = logistic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
