package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.User;

public class FeedbackDto extends FeedbackSaveDto {
        private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}