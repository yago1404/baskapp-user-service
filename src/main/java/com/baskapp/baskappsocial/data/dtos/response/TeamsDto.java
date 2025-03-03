package com.baskapp.baskappsocial.data.dtos.response;

import com.baskapp.baskappsocial.data.models.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsDto {
    private List<TeamDto> teams;

    public static TeamsDto fromModels(List<Team> teams) {
        List<TeamDto> teamsDtos = new ArrayList<>();
        for (Team team : teams) {
            teamsDtos.add(TeamDto.fromModel(team));
        }

        return new TeamsDto(teamsDtos);
    }
}
