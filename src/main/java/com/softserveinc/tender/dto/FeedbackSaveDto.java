package com.softserveinc.tender.dto;


public class FeedbackSaveDto {
    private Integer communication;
    private Integer speed;
    private Integer logistic;
    private String comment;


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
