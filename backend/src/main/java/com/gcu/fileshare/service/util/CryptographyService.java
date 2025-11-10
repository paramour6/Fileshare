package com.gcu.fileshare.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CryptographyService
{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String hashPassword(String passwordToHash)
    {
        log.info("[CryptographyService.hashPassword] hashPassword method called.");

        return passwordEncoder.encode(passwordToHash);
    }

    public boolean verifyPassword(String password, String actualPasswordHash)
    {
        log.info("[CryptographyService.verify] verifyPassword method called.");

        return passwordEncoder.matches(password, actualPasswordHash);
    }
}