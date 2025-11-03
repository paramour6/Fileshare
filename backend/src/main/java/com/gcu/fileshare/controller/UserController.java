package com.gcu.fileshare.controller;

import com.gcu.fileshare.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.fileshare.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcu.fileshare.config.AuthenticatedController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController extends AuthenticatedController
{
    @Autowired
    private UserService userService;

    @GetMapping("/curuserid")
    public ResponseEntity<?> getCurrentUserId()
    {
        try
        {
            Optional<UserDto> user = userService.findUserByUsername(getRequestingUser().getUsername());

            if(user.isEmpty())
            {
                throw new Exception();
            }

            log.info("[UserController] Current user ID: " + user.get().getId());
            return ResponseEntity.ok(user.get().getId());
        }
        catch(Exception e)
        {
            return ResponseEntity.internalServerError().body("There was an error getting the current user ID!");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestParam(required=false) String username, @RequestParam(required=false) String emailAddress)
    {
        try
        {
            if(username == null && emailAddress == null)
            {
                log.info("[UserController] Getting all users");

                return ResponseEntity.ok(userService.findAllUsers());
            }
            else if(emailAddress == null)
            {
                log.info("[UserController] Getting user by username");

                Optional<UserDto> user = userService.findUserByUsername(username);

                if(user.isPresent())
                {
                    return ResponseEntity.ok(user.get());
                }
                else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username " + username + " does not exist!");
            }
            else
            {
                log.info("[UserController] Getting user by email address");

                Optional<UserDto> user = userService.findUserByEmailAddress(emailAddress);

                if(user.isPresent())
                {
                    return ResponseEntity.ok(user.get());
                }
                else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email address " + emailAddress + " does not exist!");
            }
        }
        catch(Exception e)
        {
            log.error("[UserController] Exception caught in UserService.getAllUsers!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error getting all users!");
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id)
    {
        log.info("[UserController] Getting user by ID");

        try
        {
            Optional<UserDto> user = userService.findUserById(id);

            if(user.isPresent())
            {
                return ResponseEntity.ok(user.get());
            }
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User ID " + id + " does not exist!");
        }
        catch(Exception e)
        {
            log.error("[UserController] Caught exception in UserService.getUserById!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error getting user by ID!");
        }
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDto userDto)
    {
        log.info("[UserController] Updating user");

        try
        {
            if(id != userDto.getId())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User IDs do not match!");
            }

            Optional<UserDto> updatedUser = userService.updateUser(userDto);

            if(updatedUser.isPresent())
            {
                return ResponseEntity.ok(updatedUser.get());
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request to update user!");
        }
        catch(Exception e)
        {
            log.error("[UserController] Caught exception is UserService.updateUser!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error updating the user!");
        }
    }
}