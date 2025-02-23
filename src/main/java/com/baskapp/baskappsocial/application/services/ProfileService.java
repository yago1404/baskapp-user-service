package com.baskapp.baskappsocial.application.services;

import com.baskapp.baskappsocial.data.dtos.request.CreateProfileDto;
import com.baskapp.baskappsocial.data.dtos.request.UpdateProfileDto;
import com.baskapp.baskappsocial.data.dtos.response.ProfileDto;
import com.baskapp.baskappsocial.data.dtos.response.ProfilesDto;
import com.baskapp.baskappsocial.data.models.Profile;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.data.models.enums.UserRule;
import com.baskapp.baskappsocial.data.repositories.ProfileRepository;
import com.baskapp.baskappsocial.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileDto getProfile(User user) {
        if (user.getProfile() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não possui um perfil associado");
        }

        Optional<Profile> profile = this.profileRepository.findById(user.getProfile().getId());

        if (profile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "perfil não encontrado");
        }

        return ProfileDto.fromModel(profile.get());
    }

    public ProfileDto createProfile(User user, CreateProfileDto createProfileDto) {
        if (this.profileRepository.existsByUser(user)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse usuário já possui um perfil");
        }

        if (createProfileDto.getRule() == UserRule.PLAYER && (createProfileDto.getPosition() == null || createProfileDto.getHeight() == 0 || createProfileDto.getBirthday() == null || createProfileDto.getCellphone() == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posição, altura, data de nascimento e contato são obrigatórios para criar um perfil de jogador");
        }

        Profile profile = new Profile();
        profile.setName(createProfileDto.getName());
        profile.setCellphone(createProfileDto.getCellphone());
        profile.setBirthday(createProfileDto.getBirthday());
        profile.setHeight(createProfileDto.getHeight());
        profile.setPosition(createProfileDto.getPosition());
        profile.setRule(createProfileDto.getRule());
        profile.setOpen(false);
        profile.setUser(user);

        profile = this.profileRepository.save(profile);

        user.setProfile(profile);
        this.userRepository.save(user);

        return ProfileDto.fromModel(profile);
    }

    public ProfileDto changeProfile(User user, UpdateProfileDto updateProfile) {
        if (!this.profileRepository.existsByUser(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não possui perfil");
        }

        Optional<Profile> profile = this.profileRepository.findById(user.getProfile().getId());
        if (profile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }

        if (updateProfile.getName() != null) profile.get().setName(updateProfile.getName());
        if (updateProfile.getCellphone() != null) profile.get().setCellphone(updateProfile.getCellphone());
        if (updateProfile.getBirthday() != null) profile.get().setBirthday(updateProfile.getBirthday());
        if (updateProfile.getHeight() != 0) profile.get().setHeight(updateProfile.getHeight());
        if (updateProfile.getPosition() != null) profile.get().setPosition(updateProfile.getPosition());
        if (updateProfile.getOpen() != null) profile.get().setOpen(updateProfile.getOpen());

        this.profileRepository.save(profile.get());

        user.setProfile(profile.get());
        this.userRepository.save(user);

        return ProfileDto.fromModel(profile.get());
    }

    public ProfilesDto getOpenProfiles(Pageable pageable) {
        List<ProfileDto> profilesList = new ArrayList<>();
        Page<Profile> profiles = this.profileRepository.findByOpen(true, pageable);

        for (Profile profile : profiles) {
            profilesList.add(ProfileDto.fromModel(profile));
        }

        return new ProfilesDto(profilesList);
    }
}
