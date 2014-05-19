package com.softserveinc.tender.entity;

import javax.persistence.*;

@Entity
@Table(name = "checked_profile")
public class CheckedProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false)
    private User moderator;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private CheckedStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public CheckedStatus getStatus() {
        return status;
    }

    public void setStatus(CheckedStatus status) {
        this.status = status;
    }
}
