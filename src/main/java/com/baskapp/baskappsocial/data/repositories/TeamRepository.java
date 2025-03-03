package com.baskapp.baskappsocial.data.repositories;

import com.baskapp.baskappsocial.data.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    Optional<List<Team>> findByCoachId(UUID id);
}
