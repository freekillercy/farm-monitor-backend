package com.farmmonitorbackend.models;

import com.farmmonitorbackend.utils.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class UserDTO {
    private String email;
    private UserRoles[] roles;
}
