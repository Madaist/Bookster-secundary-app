package com.rest.Register.controllers;

import com.rest.Register.domain.User;
import com.rest.Register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;



@RestController
public class RegisterPageCotroller {

    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/post-register", produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerPagePost( @Valid @RequestBody  User user) {

        return user;
    }

    @GetMapping(value = "/get-register/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerPageGet(@PathVariable("id")  int id) {

        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/save-user")
    public void saveUser(){
        User user = restTemplate.getForObject("http://localhost:8080/save-user", User.class);
        user.setLastName("fr45t45t4");
        try {
            userService.addUser(user);
        } catch (Exception e) {
           e.printStackTrace();
        }

    }

}
