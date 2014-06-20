package com.softserveinc.tender.dto;

import javax.validation.constraints.NotNull;

public class PrivateSellerRegistrationDataDto {

    @NotNull
    private UserDto userDto;

    @NotNull
    private ProfileDto profileDto;

    @NotNull
    private TradeSphereDto tradeSphereDto;

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

    public TradeSphereDto getTradeSphereDto() {
        return tradeSphereDto;
    }

    public void setTradeSphereDto(TradeSphereDto tradeSphereDto) {
        this.tradeSphereDto = tradeSphereDto;
    }
}
