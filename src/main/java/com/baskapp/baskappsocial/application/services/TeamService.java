package com.baskapp.baskappsocial.application.services;

import com.baskapp.baskappsocial.data.dtos.request.AddPlayerDto;
import com.baskapp.baskappsocial.data.dtos.request.CreateTeamDto;
import com.baskapp.baskappsocial.data.dtos.request.UpdateTeamDto;
import com.baskapp.baskappsocial.data.dtos.response.TeamDto;
import com.baskapp.baskappsocial.data.dtos.response.TeamsDto;
import com.baskapp.baskappsocial.data.models.Profile;
import com.baskapp.baskappsocial.data.models.Team;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.data.repositories.ProfileRepository;
import com.baskapp.baskappsocial.data.repositories.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProfileRepository profileRepository;

    public TeamDto createTeam(User user, CreateTeamDto dto) {
        if (!user.getProfile().isCoaching()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É preciso ser um técnico para criar um time");
        }

        Team team = new Team();
        team.setName(dto.getName());
        team.setCoach(user.getProfile());

        Team newTeam = teamRepository.save(team);
        return TeamDto.fromModel(newTeam);
    }

    public TeamsDto getMyTeams(User user) {
        Optional<List<Team>> optionalTeam = teamRepository.findByCoachId(user.getProfile().getId());

        if (optionalTeam.isEmpty() || optionalTeam.get().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sem times associados ao perfil");
        }

        List<Team> teams = optionalTeam.get();

        return TeamsDto.fromModels(teams);
    }

    public TeamDto addPlayerToTeam(User user, AddPlayerDto dto) {
        if (!user.getProfile().isCoaching()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É preciso ser tecnico para adicionar um jogador");
        }

        Optional<Team> team = this.teamRepository.findById(dto.getTeamId());

        if (team.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado");
        }

        if (!team.get().isTeamCoach(user.getProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É preciso ser técnico do time para adicionar um jogador");
        }

        Optional<Profile> previousProfile = team.get()
                .getPlayers()
                .stream()
                .filter(player -> player.getId().equals(dto.getProfileId()))
                .findFirst();

        if (previousProfile.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse jogador já esta associado ao time");
        }

        Optional<Profile> profile = this.profileRepository.findById(dto.getProfileId());

        if (profile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil do jogador não encontrado");
        }

        if (profile.get().isCoaching()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Um tecnico nao pode ser associado como jogador");
        }

        team.get().getPlayers().add(profile.get());
        Team savedTeam = this.teamRepository.save(team.get());

        return TeamDto.fromModel(savedTeam);
    }

    public TeamDto changeTeam(User user, UUID teamId, @Valid UpdateTeamDto dto) {
        if (!user.getProfile().isCoaching()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É preciso ser um tecnico para atualizar o time");
        }

        Optional<Team> optionalTeam = this.teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado");
        }

        Team team = optionalTeam.get();

        if (!team.isTeamCoach(user.getProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É preciso ser o tecnico do time para atualiza-lo");
        }

        if (dto.getCoachId() != null) this.associateProfileToCoachTeam(dto.getCoachId(), team);
        if (dto.getName() != null) team.setName(dto.getName());

        Team savedTeam = this.teamRepository.save(team);

        return TeamDto.fromModel(savedTeam);
    }

    private void associateProfileToCoachTeam(UUID profileId, Team team) {
        Optional<Profile> optionalProfile = this.profileRepository.findById(profileId);
        if (optionalProfile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado");
        }

        Profile profile = optionalProfile.get();
        if (!profile.isCoaching()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Novo perfil precisa ser um tecnico");
        }

        team.setCoach(profile);
    }

    public TeamDto removePlayerFromTeam(User user, UUID playerId, UUID teamId) {
        Optional<Team> optionalTeam = this.teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time nao encontrado");
        }

        Team team = optionalTeam.get();

        if (!user.getProfile().isCoaching() || !team.isTeamCoach(user.getProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "É preciso ser o tecnico do time para remover um jogador");
        }

        Optional<Profile> optionalProfile = team
                .getPlayers()
                .stream()
                .filter(player -> player.getId().equals(playerId))
                .findFirst();

        if (optionalProfile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogador nao pertence a esse time");
        }

        team.getPlayers().remove(optionalProfile.get());

        this.teamRepository.save(team);
        return TeamDto.fromModel(team);
    }
}
