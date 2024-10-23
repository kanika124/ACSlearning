package com.example.LoginPage.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private Long phone;
    private String roleName;
    private String password;
    private String description;
    private boolean status;
    private boolean readPermission;
    private boolean writePermission;
    private boolean updatePermission;
}
