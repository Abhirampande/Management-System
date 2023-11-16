package com.management.managementsystem.repositories;

import com.management.managementsystem.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {
    // custom method
    Optional<RefreshToken> findByRefreshToken(String token);
}
