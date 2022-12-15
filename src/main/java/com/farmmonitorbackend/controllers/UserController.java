package com.farmmonitorbackend.controllers;

import com.farmmonitorbackend.exceptions.UserAlreadyExistsException;
import com.farmmonitorbackend.exceptions.UserNotFoundException;
import com.farmmonitorbackend.models.User;
import com.farmmonitorbackend.models.UserDTO;
import com.farmmonitorbackend.services.HttpService;
import com.farmmonitorbackend.services.UserService;
import com.farmmonitorbackend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    HttpService httpService;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDto) {
        try {
            if (service.getUserByEmail(userDto.getEmail()).isEmpty()) {
                User user = UserUtils.userDTOToUser(userDto);
                UserDTO newUser = UserUtils.userToUserDTO(service.createUser(user));
                return httpService.sendResponse(HttpStatus.OK, newUser);
            } else {
                throw new UserAlreadyExistsException(userDto.getEmail());
            }
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            return httpService.sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDto) {
        try {
            Optional<User> optionalUser = service.getUserByEmail(userDto.getEmail());
            if (optionalUser.isPresent()) {
                User userToUpdate = UserUtils.userDTOToUser(userDto);
                userToUpdate.setId(optionalUser.get().getId());

                UserDTO newUser = UserUtils.userToUserDTO(service.updateUser(userToUpdate));
                return httpService.sendResponse(HttpStatus.OK, newUser);
            } else {
                throw new UserNotFoundException(userDto.getEmail());
            }
        } catch (UserNotFoundException e) {
            return httpService.sendResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return httpService.sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getUserByEmail(@RequestBody String email) {
        try {
            Optional<User> optionalUser = service.getUserByEmail(email);
            if (optionalUser.isPresent()) {
                return httpService.sendResponse(HttpStatus.OK, UserUtils.userToUserDTO(optionalUser.get()));
            } else {
                throw new UserNotFoundException(email);
            }
        } catch (UserNotFoundException e) {
            return httpService.sendResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return httpService.sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestBody String email) {
        try {
            Optional<User> optionalUser = service.getUserByEmail(email);
            if (optionalUser.isPresent()) {
                service.deleteUser(optionalUser.get().getId());
                return httpService.sendResponse(HttpStatus.OK, "User successfully deleted!");
            } else {
                throw new UserNotFoundException(email);
            }
        } catch (UserNotFoundException e) {
            return httpService.sendResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return httpService.sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
