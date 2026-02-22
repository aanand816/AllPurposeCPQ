package com.allpurposecpq.backend.auth.repository;

import com.allpurposecpq.backend.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Find refresh token by token string
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Find all refresh tokens for a specific user
     */
    List<RefreshToken> findByUserId(Long userId);

    /**
     * Check if a refresh token exists
     */
    boolean existsByToken(String token);

    /**
     * Check if user has any active refresh tokens
     */
    boolean existsByUserIdAndExpiryDateAfter(Long userId, OffsetDateTime now);

    /**
     * Find all valid (non-expired) refresh tokens for a user
     */
    @Query("SELECT rt FROM RefreshToken rt WHERE rt.userId = :userId AND rt.expiryDate > CURRENT_TIMESTAMP")
    List<RefreshToken> findValidTokensByUserId(@Param("userId") Long userId);

    /**
     * Find all expired refresh tokens
     */
    @Query("SELECT rt FROM RefreshToken rt WHERE rt.expiryDate <= CURRENT_TIMESTAMP")
    List<RefreshToken> findExpiredTokens();

    /**
     * Delete refresh token by token string
     */
    void deleteByToken(String token);

    /**
     * Delete all refresh tokens for a specific user
     */
    @Modifying
    @Transactional
    void deleteByUserId(Long userId);

    /**
     * Delete all expired refresh tokens
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken rt WHERE rt.expiryDate <= CURRENT_TIMESTAMP")
    void deleteExpiredTokens();

    /**
     * Count all active tokens for a user
     */
    long countByUserIdAndExpiryDateAfter(Long userId, OffsetDateTime now);

    /**
     * Find the most recent refresh token for a user
     */
    @Query("SELECT rt FROM RefreshToken rt WHERE rt.userId = :userId ORDER BY rt.createdDate DESC LIMIT 1")
    Optional<RefreshToken> findMostRecentTokenByUserId(@Param("userId") Long userId);
}
