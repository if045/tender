package com.softserveinc.tender.dto;

import javax.validation.constraints.NotNull;

public class PrivateCustomerRegistrationDataDto {

    @NotNull
    private UserDto userDto;

    @NotNull
    private ProfileDto profileDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
    }
}
