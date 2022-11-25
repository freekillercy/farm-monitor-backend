package com.farmmonitorbackend.controllers;

import com.farmmonitorbackend.models.User;
import com.farmmonitorbackend.models.UserDTO;
import com.farmmonitorbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/create")
    private ResponseEntity<Object> createUser(@RequestBody User user) {
        try{
            User newUser = service.createUser(user);
            return ResponseEntity.ok().body(new UserDTO(newUser.getEmail(), newUser.getRoles()));
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    private ResponseEntity<Object> updateUser(@RequestBody User user) {
        /*try{
            if (service.getUserByEmail(user.getEmail()).isEmpty()) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            else {
                User newUser = service.createUser(user);
                return ResponseEntity.ok().body(new UserDTO(newUser.getEmail(), newUser.getRoles()));
            }
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }*/
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    private ResponseEntity<User> getUserByEmail(@RequestBody String email) {
        /*Optional<User> optionalUser = service.getUserByEmail(email);

        return optionalUser.isPresent() ?
                ResponseEntity.ok().body(optionalUser.get()):
                ResponseEntity.notFound().build();*/
        User user = service.getUserByEmail(email);
        return ResponseEntity.ok().body(user);

    }

    @DeleteMapping("/delete")
    private ResponseEntity<String> deleteUser(@RequestBody String email) {
        /*try{
            User user = service.getUserByEmail(email);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            else {
                service.deleteUser(optionalUser.get().getId());
                return ResponseEntity.ok().build();
            }
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }*/
        //return ResponseEntity.ok().body(service.getUserByEmail(email));
        return ResponseEntity.ok().build();
    }

}
