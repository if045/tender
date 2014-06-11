package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.User;

public class FeedbackDto {

    private Integer id;
    private Integer userId;
    private Integer profileId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
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


