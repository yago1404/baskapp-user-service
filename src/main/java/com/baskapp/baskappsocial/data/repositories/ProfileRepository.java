package com.baskapp.baskappsocial.data.repositories;

import com.baskapp.baskappsocial.data.models.Profile;
import com.baskapp.baskappsocial.data.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    boolean existsByUser(User user);

    @Query("SELECT p FROM Profile p WHERE p.open = :open")
    Page<Profile> findByOpen(Boolean open, Pageable pageable);
}
