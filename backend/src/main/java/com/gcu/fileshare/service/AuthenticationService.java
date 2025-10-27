package com.gcu.fileshare.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.fileshare.dao.entity.UserEntity;
import com.gcu.fileshare.dto.UserDto;

@Service
@Slf4j
public class AuthenticationService
{
    @Autowired
    private CryptographyService cryptographyService;
    @Autowired
    private UserService userService;

    public UserDto login(String username, String password)
    {
        log.info("AuthenticationService.login: login method called.");

        UserEntity userEntity = userService.findUserByUsername(username);

        if(userEntity == null)
        {
            return null;
        }

        if(cryptographyService.verifyPassword(password, userEntity.getPasswordHash()))
        {
            return MapperService.toDto(userEntity);
        }
        else return null;
    }

    public UserDto register(UserDto userDto)
    {
        log.info("AuthenticationService.register: register method called");

        try
        {
            UserEntity userEntity = userService.createUser(userDto);
            if(userEntity != null)
            {
                return MapperService.toDto(userEntity);
            }
        }
        catch(Exception e)
        {
            log.error("AuthenticationService.register: Exception caught in userService.createUser!");
        }

        return null;
    }
}