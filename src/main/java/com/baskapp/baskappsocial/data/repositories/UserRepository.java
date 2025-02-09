package com.baskapp.baskappsocial.data.repositories;

import com.baskapp.baskappsocial.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT COUNT(u) > 0 FROM appUser u WHERE u.email = :email")
    Boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM appUser u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
