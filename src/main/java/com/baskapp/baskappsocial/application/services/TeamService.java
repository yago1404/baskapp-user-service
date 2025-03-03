package com.baskapp.baskappsocial.application.services;

import com.baskapp.baskappsocial.data.dtos.request.CreateTeamDto;
import com.baskapp.baskappsocial.data.dtos.response.TeamDto;
import com.baskapp.baskappsocial.data.models.Team;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.data.models.enums.UserRule;
import com.baskapp.baskappsocial.data.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public TeamDto createTeam(User user, CreateTeamDto dto) {
        if (user.getProfile().getRule() != UserRule.COACH && user.getProfile().getRule() != UserRule.ADMIN) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso ser um técnic para criar um time");
        }

        Team team = new Team();
        team.setName(dto.getName());
        team.setCoach(user.getProfile());

        Team newTeam = teamRepository.save(team);
        return TeamDto.fromModel(newTeam);
    }
}
