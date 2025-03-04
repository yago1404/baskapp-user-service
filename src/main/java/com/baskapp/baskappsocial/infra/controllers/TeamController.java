package com.baskapp.baskappsocial.infra.controllers;

import com.baskapp.baskappsocial.application.services.TeamService;
import com.baskapp.baskappsocial.data.dtos.request.AddPlayerDto;
import com.baskapp.baskappsocial.data.dtos.request.CreateTeamDto;
import com.baskapp.baskappsocial.data.dtos.request.UpdateTeamDto;
import com.baskapp.baskappsocial.data.dtos.response.ResponseBodyDto;
import com.baskapp.baskappsocial.data.dtos.response.TeamDto;
import com.baskapp.baskappsocial.data.dtos.response.TeamsDto;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.infra.notations.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Authenticated
    @GetMapping("/my-teams")
    public ResponseEntity<ResponseBodyDto<TeamsDto>> getMyTeams(HttpServletRequest request) {
        User user = (User) request.getAttribute("authenticatedUser");

        TeamsDto teams = teamService.getMyTeams(user);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyDto<>("success", 200, teams));
    }

    @Authenticated
    @PostMapping
    public ResponseEntity<ResponseBodyDto<TeamDto>> createTeam(HttpServletRequest request, @Valid @RequestBody CreateTeamDto dto) {
        User user = (User) request.getAttribute("authenticatedUser");

        TeamDto team = teamService.createTeam(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBodyDto<>("created", 201, team));
    }

    @Authenticated
    @PostMapping("/player")
    public ResponseEntity<ResponseBodyDto<TeamDto>> addPlayer(HttpServletRequest request, @Valid @RequestBody AddPlayerDto dto) {
        User user = (User) request.getAttribute("authenticatedUser");

        TeamDto team = this.teamService.addPlayerToTeam(user, dto);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyDto<>("success", 200, team));
    }

    @Authenticated
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<TeamDto>> changeTeam(HttpServletRequest request, @PathVariable UUID id, @Valid @RequestBody UpdateTeamDto dto) {
        User user = (User) request.getAttribute("authenticatedUser");

        TeamDto team = this.teamService.changeTeam(user, id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyDto<>("success", 200, team));
    }

    @Authenticated
    @DeleteMapping("/{teamId}/player/{playerId}")
    public ResponseEntity<ResponseBodyDto<TeamDto>> removePlayer(
            HttpServletRequest request,
            @PathVariable UUID playerId,
            @PathVariable UUID teamId
    ) {
        User user = (User) request.getAttribute("authenticatedUser");

        TeamDto team = this.teamService.removePlayerFromTeam(user, playerId, teamId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyDto<>("success", 200, team));
    }
}
