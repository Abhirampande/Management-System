package com.management.managementsystem.services;

import com.management.managementsystem.entities.User;
import com.management.managementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    //private List<User> store= new ArrayList<>();

    //for in code authentication
    //public UserService() {
        //store.add(new User(UUID.randomUUID().toString(),"Abhiram Pandey","Abhiram@ct.in"));
    //}
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User> getUsers(){

        return userRepository.findAll();
        //return this.store;

    }

    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

