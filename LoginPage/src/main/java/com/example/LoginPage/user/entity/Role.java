package com.example.LoginPage.user.entity;

import jakarta.persistence.*;

@Entity
//@Getter
//@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    private Boolean status;
    private String description;
    @OneToOne
    @JoinColumn
    private User user;
    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL)
    private Permission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission getPermission() {
        return permission;
    }

}
