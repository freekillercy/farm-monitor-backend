package com.farmmonitorbackend.utils;

import com.farmmonitorbackend.models.User;
import com.farmmonitorbackend.models.UserDTO;

public class UserUtils {

    private UserUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());

        return user;
    }

    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());

        return userDTO;
    }

}
