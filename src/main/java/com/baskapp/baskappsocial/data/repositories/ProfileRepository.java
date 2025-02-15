package com.baskapp.baskappsocial.data.repositories;

import com.baskapp.baskappsocial.data.models.Profile;
import com.baskapp.baskappsocial.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    boolean existsByUser(User user);
}
