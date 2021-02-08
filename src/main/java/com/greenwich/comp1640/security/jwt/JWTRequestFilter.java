package com.greenwich.comp1640.security.jwt;

import com.greenwich.comp1640.exception.CustomExceptions;
import com.greenwich.comp1640.security.jwt.util.JWTUtil;
import com.greenwich.comp1640.util.Util;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenString = request.getHeader("Authorization");

        if (Util.isNotBlank(tokenString) && tokenString.startsWith("Bearer ")) {
            String accessToken = tokenString.substring(7);

            try {
                    String username = jwtUtil.extractUsername(accessToken);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } catch (ExpiredJwtException ex) {
                String requestUrl = request.getRequestURI();

                System.out.println(ex.getMessage());

                if (requestUrl.contains("refresh-token")) {
                    allowForRefreshToken(ex, request);
                } else {
                    throw new CustomExceptions.BadRequestException("Bad request");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(null, null, null);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        request.setAttribute("claims", ex.getClaims());
    }

}
