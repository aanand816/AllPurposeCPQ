package com.allpurposecpq.backend.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AU_USER_ROLE_XR")
public class AuUserRoleXr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    // Constructors
    public AuUserRoleXr() {
    }

    public AuUserRoleXr(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
        this.isActive = true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
