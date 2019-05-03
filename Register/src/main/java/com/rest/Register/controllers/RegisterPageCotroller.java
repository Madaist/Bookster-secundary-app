package com.rest.Register.controllers;

import com.rest.Register.domain.Booking;
import com.rest.Register.domain.Booking1;
import com.rest.Register.domain.CompositePK;
import com.rest.Register.domain.User;
import com.rest.Register.services.BookingService;
import com.rest.Register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/post-register", produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerPagePost(@Valid @RequestBody User user) {

        return user;
    }

    @GetMapping(value = "/get-register/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerPageGet(@PathVariable("id") int id) {

        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

  /*  @GetMapping(value = "/save-booking/{userId}/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> saveBooking1(@PathVariable("userId") int userId,
                                               @PathVariable("bookId") int bookId){
        Booking booking = new Booking(new CompositePK(userId, bookId));
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }*/

    @GetMapping(value = "/save-booking-param", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking1> saveBooking2(@RequestParam("userId") Integer userId,
                                                @RequestParam("bookId") Integer bookId){
        Booking1 booking = new Booking1(userId, bookId);
//        return new ResponseEntity<>(booking, HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");


        return ResponseEntity.ok()
                .headers(headers)
                .body(booking);
    }

    /*@PostMapping(value = "/save-user")
    public void saveUser(@Valid @RequestBody User user) {

        if (user.getEmail().length() > 10) {
            String message = "Email too long";
            restTemplate.postForObject("http://localhost:8080/saving-failed", message, String.class);
        }
        else {
            try {
                userService.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

     */

    @PostMapping(value = "/save-user")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {

        if (user.getPassword().length() < 4) {
           // ResponseEntity responseEntity = new ResponseEntity("Email too long", HttpStatus.FORBIDDEN);
           // restTemplate.postForObject("http://localhost:8080/saving-failed", responseEntity, ResponseEntity.class);
            return new ResponseEntity("Password too short", HttpStatus.LENGTH_REQUIRED);
        } else {
            try {
                userService.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity(HttpStatus.OK);


        /*
        try{
            if(user.getPassword().length() < 4) {
                throw new Exception();
            }
            else{
                userService.addUser(user);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity("Password too short", HttpStatus.LENGTH_REQUIRED);
        }
        return new ResponseEntity(HttpStatus.OK);*/

    }
}
