package com.example.LoginPage.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Getter
//@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean readPermission;
    private Boolean writePermission;
    private Boolean updatePermission;

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReadPermission() {
        return readPermission;
    }

    public void setReadPermission(Boolean readPermission) {
        this.readPermission = readPermission;
    }

    public Boolean getWritePermission() {
        return writePermission;
    }

    public void setWritePermission(Boolean writePermission) {
        this.writePermission = writePermission;
    }

    public Boolean getUpdatePermission() {
        return updatePermission;
    }

    public void setUpdatePermission(Boolean updatePermission) {
        this.updatePermission = updatePermission;
    }

    @OneToOne
    @JoinColumn
    private Role role;
}
