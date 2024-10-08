package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.User;
import com.proj2g17.proj2g17.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String username) {
        Optional<User> userOptional = userRepository.findByEmailAndUsername(email, username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setIs_admin(false); // default value
            return userRepository.save(newUser);
        }

    }
    @DeleteMapping("/deleteAcc")
    public ResponseEntity<Void> deleteAccount(@RequestParam String email, @RequestParam String username) {
        Optional<User> userOptional = userRepository.findByEmailAndUsername(email, username);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<User> getByUser_id(Integer user_id) {
        return userRepository.findById(user_id);
    }

}
