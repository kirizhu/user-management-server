package com.example.usermanagementserver.controller;

import com.example.usermanagementserver.dao.UserRepository;
import com.example.usermanagementserver.exceptions.ResourceNotFoundException;
import com.example.usermanagementserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public String saveUser(@RequestBody User user){
        userRepository.save(user);
        return "User saved";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public User  getUserById(@PathVariable int id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new ResourceNotFoundException("Could not find user with id: "+id);
        }
    }
    @PutMapping("/users/{id}")
    public User  updateUser(@PathVariable int id, @RequestBody User userDetails) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setFirstName(userDetails.getFirstName());
            user.get().setLastName(userDetails.getLastName());
            user.get().setEmail(userDetails.getEmail());

            User updatedUser = userRepository.save(user.get());
            return updatedUser;
        }else{
            throw new ResourceNotFoundException("Could not find user with id: "+id);
        }
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "Deleted: "+ user.get().getFirstName();
        }else{
            throw new ResourceNotFoundException("Could not find user with id: "+id);
        }
    }
}
