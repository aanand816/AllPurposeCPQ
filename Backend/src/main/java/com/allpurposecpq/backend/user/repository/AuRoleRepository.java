package com.allpurposecpq.backend.user.repository;

import com.allpurposecpq.backend.user.model.AuRole;
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
public interface AuRoleRepository extends JpaRepository<AuRole, Long> {

    /**
     * Find role by role name
     */
    Optional<AuRole> findByRoleName(String roleName);

    /**
     * Find all active roles
     */
    List<AuRole> findByIsActiveTrue();

    /**
     * Find all inactive roles
     */
    List<AuRole> findByIsActiveFalse();

    /**
     * Check if role name exists
     */
    boolean existsByRoleName(String roleName);

    /**
     * Check if role name exists for another role (useful for updates)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM AuRole r WHERE r.roleName = :roleName AND r.id != :roleId")
    boolean existsByRoleNameAndIdNot(@Param("roleName") String roleName, @Param("roleId") Long roleId);

    /**
     * Find roles with pagination
     */
    Page<AuRole> findAll(Pageable pageable);

    /**
     * Find active roles with pagination
     */
    Page<AuRole> findByIsActiveTrue(Pageable pageable);

    /**
     * Search roles by role name or description
     */
    @Query("SELECT r FROM AuRole r WHERE r.roleName LIKE %:searchTerm% OR r.description LIKE %:searchTerm%")
    List<AuRole> searchRolesByNameOrDescription(@Param("searchTerm") String searchTerm);

    /**
     * Search roles with pagination
     */
    @Query("SELECT r FROM AuRole r WHERE r.roleName LIKE %:searchTerm% OR r.description LIKE %:searchTerm%")
    Page<AuRole> searchRolesByNameOrDescription(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Count all active roles
     */
    long countByIsActiveTrue();

    /**
     * Count all inactive roles
     */
    long countByIsActiveFalse();

    /**
     * Deactivate a role
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuRole r SET r.isActive = false WHERE r.id = :roleId")
    void deactivateRole(@Param("roleId") Long roleId);

    /**
     * Activate a role
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuRole r SET r.isActive = true WHERE r.id = :roleId")
    void activateRole(@Param("roleId") Long roleId);

    /**
     * Deactivate all roles
     */
    @Modifying
    @Transactional
    @Query("UPDATE AuRole r SET r.isActive = false")
    void deactivateAllRoles();

    /**
     * Find roles by description
     */
    @Query("SELECT r FROM AuRole r WHERE r.description LIKE %:description%")
    List<AuRole> findByDescriptionContaining(@Param("description") String description);

    /**
     * Get all role names
     */
    @Query("SELECT r.roleName FROM AuRole r WHERE r.isActive = true")
    List<String> findAllActiveRoleNames();
}
