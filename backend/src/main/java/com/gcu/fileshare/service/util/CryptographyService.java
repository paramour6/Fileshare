package com.gcu.fileshare.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility service for cryptography
 */
@Service
@Slf4j
public class CryptographyService
{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /** 
     * @param passwordToHash The password to be hashed
     * @return The hashed password
     */
    public String hashPassword(String passwordToHash)
    {
        log.info("[CryptographyService.hashPassword] hashPassword method called.");

        return passwordEncoder.encode(passwordToHash);
    }

    /** 
     * @param password The password to check
     * @param actualPasswordHash The actal password hash of the plain-text password
     * @return True if passwords match, false if otherwise
     */
    public boolean verifyPassword(String password, String actualPasswordHash)
    {
        log.info("[CryptographyService.verify] verifyPassword method called.");

        return passwordEncoder.matches(password, actualPasswordHash);
    }
}