package com.baskapp.baskappsocial.data.dtos.response;

import com.baskapp.baskappsocial.data.models.Profile;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.data.models.enums.PlayerPosition;
import com.baskapp.baskappsocial.data.models.enums.UserRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class ProfileDto {
    private UUID id;
    private String name;
    private String cellphone;
    private Date birthday;
    private int height;
    private PlayerPosition position;
    private UserRule rule;
    private String picture;
    private Boolean open;

    public static ProfileDto fromModel(Profile profile) {
        return new ProfileDto(
                profile.getId(),
                profile.getName(),
                profile.getCellphone(),
                profile.getBirthday(),
                profile.getHeight(),
                profile.getPosition(),
                profile.getRule(),
                profile.getPicture(),
                profile.getOpen()
        );
    }
}
