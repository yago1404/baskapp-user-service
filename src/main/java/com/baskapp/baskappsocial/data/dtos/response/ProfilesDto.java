package com.baskapp.baskappsocial.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProfilesDto {
    private List<ProfileDto> profiles;
}
