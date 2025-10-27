package com.gcu.fileshare.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.fileshare.dao.repository.UserRepository;
import com.gcu.fileshare.dao.entity.UserEntity;
import com.gcu.fileshare.dto.UserDto;

@Service
@Slf4j
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptographyService cryptographyService;

    public UserEntity findUserByUsername(String username)
    {
        log.info("UserService.findUserByUsername: findUserByUsername method called.");

        return userRepository.findByUsername(username);
    }

    public UserEntity findUserByEmailAddress(String emailAddress)
    {
        log.info("UserService.findUserByEmailAddress: findUserByEmailAddress method called.");
        
        return userRepository.findByEmailAddress(emailAddress);
    }

    public UserEntity createUser(UserDto userDto) throws Exception
    {
        log.info("UserService.createUser: createUser method called.");

        if(findUserByUsername(userDto.getUsername()) != null || findUserByEmailAddress(userDto.getEmailAddress()) != null)
        {
            log.error("UserService.createUser: Username or email already exists.");

            throw new Exception("Username or email address already exist!");
        }

        String passwordHash = cryptographyService.hashPassword(userDto.getPassword());

        return userRepository.save(new UserEntity(userDto.getUsername(), userDto.getEmailAddress(), passwordHash));
    }
}