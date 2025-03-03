package com.baskapp.baskappsocial.infra.controllers;

import com.baskapp.baskappsocial.application.services.TeamService;
import com.baskapp.baskappsocial.data.dtos.request.CreateTeamDto;
import com.baskapp.baskappsocial.data.dtos.response.ResponseBodyDto;
import com.baskapp.baskappsocial.data.dtos.response.TeamDto;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.infra.notations.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Authenticated
    @PostMapping
    public ResponseEntity<ResponseBodyDto<TeamDto>> createTeam(HttpServletRequest request, @Valid @RequestBody CreateTeamDto dto) {
        User user = (User) request.getAttribute("authenticatedUser");

        TeamDto team = teamService.createTeam(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBodyDto<>("created", 201, team));
    }
}
