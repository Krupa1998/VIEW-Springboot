package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private CustomizedResponse response;


    @PostMapping(value = "/auth", consumes = {

            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody UserModel user) {

        CustomizedResponse response = null;

        try {

            //create authentication object token from username and password enter and pass it to authentication provider.
            //AP will call the loadUserByUsername and check valid username and password and according load the data.
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            response = new CustomizedResponse("Login Successful", null);

        } catch (BadCredentialsException e) {

            response = new CustomizedResponse("Invalid Username and/or Password", null);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
