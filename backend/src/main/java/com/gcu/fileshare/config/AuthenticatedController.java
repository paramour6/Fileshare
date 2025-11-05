package com.gcu.fileshare.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AuthenticatedController
{
    protected UserDetails getRequestingUser()
    {
        log.info("[AuthenticatedController] Getting requesting user");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails))
        {
            return null;
        }

        return (UserDetails)authentication.getPrincipal();
    }
}