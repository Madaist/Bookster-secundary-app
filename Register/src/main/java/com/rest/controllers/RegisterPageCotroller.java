package com.rest.controllers;

import com.rest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;



@RestController
public class RegisterPageCotroller {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/post-register") //produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerPagePost( @RequestBody  User user) {

        return user;
    }

    @GetMapping("/get-register")
    public User register() {

        User user = restTemplate.getForObject("http://localhost:8000/rest-register", User.class);
       /* try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    */
        return user;
    }
}
