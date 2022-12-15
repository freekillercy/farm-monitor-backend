package com.farmmonitorbackend.models;

import com.farmmonitorbackend.utils.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private UserRoles[] roles;

}
