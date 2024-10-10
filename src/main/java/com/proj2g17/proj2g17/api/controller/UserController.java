package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.User;
import com.proj2g17.proj2g17.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ADMIN FUNCTIONS

    private Boolean validateAdmin(Integer user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided user_id is not a valid id");
        }

        if (!userOptional.get().getIs_admin()) {
            return false;
        }
        
        return true;
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam Integer user_id) {
        if (!validateAdmin(user_id)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Provided user_id is not associated with an admin account");
        }
        
        return userRepository.findAll();
    }

    @PutMapping("/createUser")
    public ResponseEntity<Void> createNewUser(@RequestParam Integer user_id, @RequestParam String email, @RequestParam String username) {
        if (!validateAdmin(user_id)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Provided user_id is not associated with an admin account");
        }

        Optional<User> userOptional = userRepository.findByEmailAndUsername(email, username);
        if (!userOptional.isPresent()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setIs_admin(false);
            userRepository.save(newUser);
        }

        return ResponseEntity.noContent().build();
    }



    // USER FUNCTIONS

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

}
