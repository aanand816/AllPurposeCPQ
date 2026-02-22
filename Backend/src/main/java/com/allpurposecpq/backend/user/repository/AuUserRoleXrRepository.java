package com.allpurposecpq.backend.user.repository;

import com.allpurposecpq.backend.user.model.AuUserRoleXr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuUserRoleXrRepository extends JpaRepository<AuUserRoleXr, Long> {

    /**
     * Find all role assignments for a specific user
     */
    List<AuUserRoleXr> findByUserId(Long userId);

    /**
     * Find all users assigned to a specific role
     */
    List<AuUserRoleXr> findByRoleId(Long roleId);

    /**
     * Find a specific user-role assignment
     */
    Optional<AuUserRoleXr> findByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * Find all active role assignments for a specific user
     */
    List<AuUserRoleXr> findByUserIdAndIsActiveTrue(Long userId);

    /**
     * Find all active role assignments for a specific role
     */
    List<AuUserRoleXr> findByRoleIdAndIsActiveTrue(Long roleId);

    /**
     * Check if a user has a specific role
     */
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * Check if a user has an active role assignment
     */
    boolean existsByUserIdAndRoleIdAndIsActiveTrue(Long userId, Long roleId);

    /**
     * Delete a specific user-role assignment
     */
    void deleteByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * Count all role assignments for a specific user
     */
    long countByUserId(Long userId);

    /**
     * Count all role assignments for a specific role
     */
    long countByRoleId(Long roleId);

    /**
     * Find all active role assignments for a user with custom query
     */
    @Query("SELECT uxr FROM AuUserRoleXr uxr WHERE uxr.userId = :userId AND uxr.isActive = true")
    List<AuUserRoleXr> findActiveRolesByUserId(@Param("userId") Long userId);

    /**
     * Find all active users assigned to a role with custom query
     */
    @Query("SELECT uxr FROM AuUserRoleXr uxr WHERE uxr.roleId = :roleId AND uxr.isActive = true")
    List<AuUserRoleXr> findActiveUsersByRoleId(@Param("roleId") Long roleId);

    /**
     * Deactivate all role assignments for a specific user
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUserRoleXr uxr SET uxr.isActive = false WHERE uxr.userId = :userId")
    void deactivateAllRolesForUser(@Param("userId") Long userId);

    /**
     * Deactivate all user assignments for a specific role
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUserRoleXr uxr SET uxr.isActive = false WHERE uxr.roleId = :roleId")
    void deactivateAllUsersForRole(@Param("roleId") Long roleId);

    /**
     * Activate all role assignments for a specific user
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuUserRoleXr uxr SET uxr.isActive = true WHERE uxr.userId = :userId")
    void activateAllRolesForUser(@Param("userId") Long userId);

    /**
     * Delete all role assignments for a specific user
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM AuUserRoleXr uxr WHERE uxr.userId = :userId")
    void deleteAllRolesByUserId(@Param("userId") Long userId);

    /**
     * Delete all user assignments for a specific role
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM AuUserRoleXr uxr WHERE uxr.roleId = :roleId")
    void deleteAllUsersByRoleId(@Param("roleId") Long roleId);
}
