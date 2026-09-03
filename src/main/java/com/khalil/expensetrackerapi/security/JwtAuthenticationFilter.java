package com.khalil.expensetrackerapi.security;


import com.khalil.expensetrackerapi.reposotories.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //get the Token from the request

        String token = getTokenFromRequest(request);
        //Validate Token
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //get username from token
            String username = jwtTokenProvider.getUsernameFromToken(token);

            //load user object from db using username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            //provide user details to Spring sec for authentication
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails
                    , null
                    , userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        //bearerToken = bearer jwt_token

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); //return just JWTToken

        }
        return null;
    }
}
