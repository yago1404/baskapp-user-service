package com.baskapp.baskappsocial.infra.controllers;

import com.baskapp.baskappsocial.application.services.ProfileService;
import com.baskapp.baskappsocial.data.dtos.request.CreateProfileDto;
import com.baskapp.baskappsocial.data.dtos.request.UpdateProfileDto;
import com.baskapp.baskappsocial.data.dtos.response.ProfileDto;
import com.baskapp.baskappsocial.data.dtos.response.ProfilesDto;
import com.baskapp.baskappsocial.data.dtos.response.ResponseBodyDto;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.infra.notations.Authenticated;
import com.baskapp.baskappsocial.infra.notations.InternalRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    @Authenticated
    public ResponseEntity<ResponseBodyDto<ProfileDto>> getProfile(HttpServletRequest request) {
        User user = (User) request.getAttribute("authenticatedUser");
        ProfileDto profileDto = this.profileService.getProfile(user);

        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, profileDto));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseBodyDto<ProfilesDto>> getOpenProfiles(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ProfilesDto profilesDto = this.profileService.getOpenProfiles(PageRequest.of(page - 1, size));

        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, profilesDto));
    }

    @InternalRequest
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<ProfileDto>> getProfileById(@PathVariable("id") String id) {
        ProfileDto profileDto = this.profileService.getProfileById(id);

        return ResponseEntity.ok().body(new ResponseBodyDto<>("success", 200, profileDto));
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<ResponseBodyDto<ProfileDto>> createProfile(HttpServletRequest request, @Valid @RequestBody CreateProfileDto profile) {
        User user = (User) request.getAttribute("authenticatedUser");
        ProfileDto profileDto = this.profileService.createProfile(user, profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBodyDto<>("success", 201, profileDto));
    }

    @PutMapping
    @Authenticated
    public ResponseEntity<ResponseBodyDto<ProfileDto>> updateProfile(HttpServletRequest request, @Valid @RequestBody UpdateProfileDto profile) {
        User user = (User) request.getAttribute("authenticatedUser");
        ProfileDto profileDto = this.profileService.changeProfile(user, profile);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBodyDto<>("success", 200, profileDto));
    }
}
