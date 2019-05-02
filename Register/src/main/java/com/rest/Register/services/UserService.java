package com.rest.Register.services;

import com.rest.Register.domain.User;
import com.rest.Register.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;



    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void addUser(User user) throws Exception {
        try {
            userRepository.save(user);
        } catch (Exception exception) {
            log.error("could not save the user", user.getEmail(), exception);
            throw new Exception("could not save the user ");
        }
    }

    public void updateUser(int ID, User user){
        int result = userRepository.updateUser(ID, user.getFirstName(), user.getLastName(),
                                                user.getEmail(), user.getPassword(), user.getUserName());
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public User findUserById(int id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();

        return user;
    }







}
