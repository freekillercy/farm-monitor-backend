package com.farmmonitorbackend.services;

import com.farmmonitorbackend.models.User;
import com.farmmonitorbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User createUser(User user) {
        return repository.insert(user);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }

}
