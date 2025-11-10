package com.gcu.fileshare.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.fileshare.config.jwt.JwtUtility;
import com.gcu.fileshare.dao.entity.UserEntity;
import com.gcu.fileshare.dao.repository.UserRepository;
import com.gcu.fileshare.dto.UserDto;
import com.gcu.fileshare.dto.auth.LoginRequestDto;
import com.gcu.fileshare.dto.auth.RegisterDto;
import com.gcu.fileshare.service.UserService;
import com.gcu.fileshare.service.util.CryptographyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CryptographyService cryptographyService;
    @Autowired
    private JwtUtility jwtUtility;

    public String login(LoginRequestDto loginRequest)
    {
        log.info("[AuthenticationService] Logging in.");

        Optional<UserEntity> userEntity = userRepository.findByUsername(loginRequest.getUsername());

        if(userEntity.isEmpty())
        {
            return "";
        }

        if(cryptographyService.verifyPassword(loginRequest.getPassword(), userEntity.get().getPasswordHash()))
        {
            log.info("[AuthenticationService] Login request verified. Sending JWT.");
            return jwtUtility.createToken(userEntity.get().getUsername());
        }

        return "";
    }

    public Optional<UserDto> register(RegisterDto registerRequest)
    {
        log.info("[AuthenticationService] Registering user.");

        return userService.createUser(registerRequest);
    }
}