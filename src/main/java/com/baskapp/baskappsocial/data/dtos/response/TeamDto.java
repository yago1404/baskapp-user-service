package com.baskapp.baskappsocial.data.dtos.response;

import com.baskapp.baskappsocial.data.models.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private String name;
    private List<ProfileDto> players;
    private ProfileDto coach;

    public static TeamDto fromModel(Team team) {
        TeamDto dto = new TeamDto();
        dto.name = team.getName();
        dto.players = ProfilesDto.fromModel(team.getPlayers()).getProfiles();
        dto.coach = ProfileDto.fromModel(team.getCoach());

        return dto;
    }
}
