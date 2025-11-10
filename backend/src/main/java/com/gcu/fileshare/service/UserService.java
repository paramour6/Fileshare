package com.gcu.fileshare.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.gcu.fileshare.dao.repository.UserRepository;
import com.gcu.fileshare.dao.entity.UserEntity;
import com.gcu.fileshare.dto.UserDto;
import com.gcu.fileshare.dto.auth.RegisterDto;
import com.gcu.fileshare.service.util.CryptographyService;
import com.gcu.fileshare.service.util.MapperService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptographyService cryptographyService;

    public List<UserDto> findAllUsers()
    {
        log.info("[UserService] findAllUsers() called.");

        List<UserEntity> userEntities = userRepository.findAll();
        
        return userEntities.stream().map(MapperService::toDto).collect(Collectors.toList());
    }

    public Optional<UserDto> findUserById(long id)
    {
        log.info("[UserService] findUserById(long id) called.");

        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(userEntity.isPresent())
        {
            return Optional.of(MapperService.toDto(userEntity.get()));
        }
        else return Optional.empty();
    }

    public Optional<UserDto> findUserByUsername(String username)
    {
        log.info("[UserService] findUserByUsername(string username) called.");

        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if(userEntity.isPresent())
        {
            return Optional.of(MapperService.toDto(userEntity.get()));
        }
        else return Optional.empty();
    }

    public Optional<UserDto> findUserByEmailAddress(String emailAddress)
    {
        log.info("[UserService] findUserByEmailAddress(string emailAddress) called.");
        
        Optional<UserEntity> userEntity = userRepository.findByEmailAddress(emailAddress);

        if(userEntity.isPresent())
        {
            return Optional.of(MapperService.toDto(userEntity.get()));
        }
        else return Optional.empty();
    }

    public Optional<UserDto> createUser(RegisterDto registerDetails)
    {
        log.info("[UserService] createUser(RegisterDto registerDetails) called.");

        if(findUserByUsername(registerDetails.getUsername()).isPresent() || findUserByEmailAddress(registerDetails.getEmailAddress()).isPresent())
        {
            return Optional.empty();
        }

        String passwordHash = cryptographyService.hashPassword(registerDetails.getPassword());
        UserEntity user = new UserEntity(registerDetails.getUsername(), registerDetails.getEmailAddress(), passwordHash);
        userRepository.save(user);

        return Optional.of(MapperService.toDto(user));
    }

    public Optional<UserDto> updateUser(UserDto user)
    {
        log.info("[UserService] updateUser(UserDto user) called.");

        Optional<UserEntity> userEntity = userRepository.findById(user.getId());

        if(userEntity.isEmpty())
        {
            return Optional.empty();
        }

        UserEntity updatedUser = userEntity.get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmailAddress(user.getEmailAddress());
        updatedUser.setPasswordHash(cryptographyService.hashPassword(user.getPassword()));

        updatedUser = userRepository.save(updatedUser);

        return Optional.of(MapperService.toDto(updatedUser));
    }
}