package com.gcu.fileshare.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract controller allowing child-controllers to get the requesting user
 */
@Slf4j
public abstract class AuthenticatedController
{
    /** 
     * @return A UserDetails object representing the requesting user
     */
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