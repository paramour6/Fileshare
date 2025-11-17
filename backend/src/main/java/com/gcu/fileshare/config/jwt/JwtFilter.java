package com.gcu.fileshare.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import com.gcu.fileshare.service.auth.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Security filter for checking and authenticating requests with/without JWTs
 */
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter
{
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtility jwtUtility;

    /** 
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String path = request.getServletPath();

        if(path.equals("/login") || path.equals("/register") || path.equals("/"))
        {
            filterChain.doFilter(request, response);
            return;
        }
        
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        try
        {
            String token = authHeader.substring(7);
            String username = jwtUtility.parseUsername(token);
    
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                log.info("[JwtFilter] JWT verified, setting authentication");
            }
        }
        catch(UsernameNotFoundException e)
        {
            log.warn("[JwtFilter] JWT subject username was not found!");
        }
        catch(Exception e)
        {
            log.warn("[JwtFilter] Invalid JWT token!");
        }

        filterChain.doFilter(request, response);
    }
}