package com.baskapp.baskappsocial.application.services;

import com.baskapp.baskappsocial.data.dtos.request.CreateProfileDto;
import com.baskapp.baskappsocial.data.dtos.response.ProfileDto;
import com.baskapp.baskappsocial.data.models.Profile;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.data.repositories.ProfileRepository;
import com.baskapp.baskappsocial.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileDto createProfile(User user, CreateProfileDto createProfileDto) {
        if (this.profileRepository.existsByUser(user)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse usuário já possui um perfil");
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
}
