package com.gcu.fileshare.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gcu.fileshare.service.AuthenticationService;
import com.gcu.fileshare.dto.UserDto;

@RestController
@Slf4j
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData)
    {
        String username = loginData.get("username");
        String password = loginData.get("password");

        if(username == null || password == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password field(s) was null!");
        }

        UserDto user = authenticationService.login(username, password);

        if(user != null)
        {
            return ResponseEntity.ok(user);
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password was incorrect!");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerData)
    {
        String username = registerData.get("username");
        String emailAddress = registerData.get("emailAddress");
        String password = registerData.get("password");

        if(username == null || emailAddress == null || password == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username, email address, or password field(s) was null!");
        }

        UserDto user = authenticationService.register(new UserDto(-1, username, emailAddress, password));

        if(user != null)
        {
            return ResponseEntity.ok(user);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering user!");
    }
}