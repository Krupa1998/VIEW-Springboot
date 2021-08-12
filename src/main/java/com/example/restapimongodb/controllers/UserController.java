package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.UserModel;
import com.example.restapimongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity getUsers() {

        CustomizedResponse response = new CustomizedResponse("A list of all the Users", userService.getAllUsers());

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") String id) {

        CustomizedResponse response = null;

        try {

            response = new CustomizedResponse("User with ID " + id,
                    Collections.singletonList(userService.getAUser(id)));

        } catch (Exception e) {
            response = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PostMapping(value = "/users", consumes = {

            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUser(@RequestBody UserModel user) {

        CustomizedResponse response = null;

        try {

            response = new CustomizedResponse("User created Successfully ", Collections.singletonList(userService.adduser(user)));

        } catch (Exception e) {

            response = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
