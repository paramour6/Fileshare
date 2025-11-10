package com.gcu.fileshare.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gcu.fileshare.dto.UserDto;
import com.gcu.fileshare.dto.auth.LoginRequestDto;
import com.gcu.fileshare.dto.auth.RegisterDto;
import com.gcu.fileshare.service.auth.AuthenticationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequest)
    {
        log.info("[AuthenticationController] Logging in user.");

        String token = authenticationService.login(loginRequest);

        if(token.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login details!");
        }

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerRequest)
    {
        log.info("[AuthenticationController] Registering user.");

        Optional<UserDto> user = authenticationService.register(registerRequest);

        if(user.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid registration details!");
        }

        return ResponseEntity.ok(user.get());
    }

    @GetMapping("/")
    public ResponseEntity<?> root()
    {
        log.info("[AuthenticationController] Request made to root endpoint...");

        return ResponseEntity.ok("You're not supposed to be here... *evil cackle*");
    }
}
