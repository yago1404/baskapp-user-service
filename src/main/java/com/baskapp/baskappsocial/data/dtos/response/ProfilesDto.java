package com.baskapp.baskappsocial.data.dtos.response;

import com.baskapp.baskappsocial.data.models.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProfilesDto {
    private List<ProfileDto> profiles;

    static ProfilesDto fromModel(List<Profile> profiles) {
        List<ProfileDto> dtos = new ArrayList<>();
        for (Profile profile : profiles) {
            dtos.add(ProfileDto.fromModel(profile));
        }
        return new ProfilesDto(dtos);
    }
}
