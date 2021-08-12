package com.example.restapimongodb.config;

import com.example.restapimongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //class stating the authentication and authorization functionalities

    @Autowired
    private UserService userService; //must be the object of class that implements UserDetails


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //this method allows us to configure user authentication

        auth.userDetailsService(userService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //this method allows us to configure user authorization

        http.csrf().disable().authorizeRequests() //list request that are committed without authentication
                .antMatchers("/users/**").permitAll() //all routs including the "/users" will be permitted without authentication
                .antMatchers("/videos/**").permitAll()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated(); //all requests except this must be authenticated first.
    }


    //Bcrypt
    @Bean //ensures that upon application loading function will be called and object will be created
    public BCryptPasswordEncoder passwordEncoder() {
        //automatically encodes the password when user tries to login

        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }

}
