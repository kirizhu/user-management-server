package com.example.usermanagementserver.controller;

import com.example.usermanagementserver.dao.UserRepository;
import com.example.usermanagementserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(){
        return "Home";
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user){
        userRepository.save(user);
        return "User saved";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }
}
