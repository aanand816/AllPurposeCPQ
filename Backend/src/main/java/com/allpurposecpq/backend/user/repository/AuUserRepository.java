package com.allpurposecpq.backend.user.repository;

import com.allpurposecpq.backend.user.model.AuUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuUserRepository extends JpaRepository<AuUser, Long> {

    /**
     * Find user by username
     */
    Optional<AuUser> findByUsername(String username);

    /**
     * Find user by email
     */
    Optional<AuUser> findByEmail(String email);

    /**
     * Find all users in a domain
     */
    List<AuUser> findByDomainId(Long domainId);

    /**
     * Find all active users
     */
    List<AuUser> findByIsActiveTrue();

    /**
     * Find all inactive users
     */
    List<AuUser> findByIsActiveFalse();

    /**
     * Find active users in a domain
     */
    List<AuUser> findByDomainIdAndIsActiveTrue(Long domainId);

    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Check if username exists for another user (useful for updates)
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AuUser u WHERE u.username = :username AND u.id != :userId")
    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("userId") Long userId);

    /**
     * Check if email exists for another user (useful for updates)
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AuUser u WHERE u.email = :email AND u.id != :userId")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("userId") Long userId);

    /**
     * Find users by domain with pagination
     */
    Page<AuUser> findByDomainId(Long domainId, Pageable pageable);

    /**
     * Find active users by domain with pagination
     */
    Page<AuUser> findByDomainIdAndIsActiveTrue(Long domainId, Pageable pageable);

    /**
     * Search users by username or email
     */
    @Query("SELECT u FROM AuUser u WHERE (u.username LIKE %:searchTerm% OR u.email LIKE %:searchTerm%) AND u.domainId = :domainId")
    List<AuUser> searchUsersByUsernameOrEmail(@Param("searchTerm") String searchTerm, @Param("domainId") Long domainId);

    /**
     * Search users with pagination
     */
    @Query("SELECT u FROM AuUser u WHERE (u.username LIKE %:searchTerm% OR u.email LIKE %:searchTerm%) AND u.domainId = :domainId")
    Page<AuUser> searchUsersByUsernameOrEmail(@Param("searchTerm") String searchTerm, @Param("domainId") Long domainId, Pageable pageable);

    /**
     * Count all users in a domain
     */
    long countByDomainId(Long domainId);

    /**
     * Count active users in a domain
     */
    long countByDomainIdAndIsActiveTrue(Long domainId);

    /**
     * Deactivate a user
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUser u SET u.isActive = false WHERE u.id = :userId")
    void deactivateUser(@Param("userId") Long userId);

    /**
     * Activate a user
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUser u SET u.isActive = true WHERE u.id = :userId")
    void activateUser(@Param("userId") Long userId);

    /**
     * Deactivate all users in a domain
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUser u SET u.isActive = false WHERE u.domainId = :domainId")
    void deactivateAllUsersInDomain(@Param("domainId") Long domainId);

    /**
     * Update user password hash
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUser u SET u.passwordHash = :passwordHash WHERE u.id = :userId")
    void updatePasswordHash(@Param("userId") Long userId, @Param("passwordHash") String passwordHash);

    /**
     * Find users by first and last name
     */
    @Query("SELECT u FROM AuUser u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    List<AuUser> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
