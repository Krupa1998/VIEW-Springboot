package com.example.restapimongodb.services;

import com.example.restapimongodb.models.UserModel;
import com.example.restapimongodb.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<UserModel> getAllUsers() {

        return userRepository.findAll();
    }


    public Optional<UserModel> getAUser(String id) throws Exception {

        Optional<UserModel> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new Exception("User with id " + id + " is not found");
        }

        return user;
    }


    public UserModel adduser(UserModel user) throws Exception {

        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty()
                || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {

            throw new Exception("User Creation Failed - at least one field is missing");

        } else if (!user.getFirstName().matches("^[a-zA-Z]*$")
                || !user.getLastName().matches("^[a-zA-Z]*$")
                || !user.getEmail().matches("^(.+)@(.+)$")) {

            throw new Exception("User Creation Failed - data entered in invalid format");
        }

        //encoding the user password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //insert user into database
        UserModel newUser = userRepository.insert(user);
        return newUser;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserModel foundUser = userRepository.findByEmail(email);

        String userEmail = foundUser.getEmail();
        String userPassword = foundUser.getPassword();

        //converting foundUser type from UserModel to UserDetails (User is child class of UserDetails so following also works)
        return new User(userEmail, userPassword, new ArrayList<>());
    }

}
