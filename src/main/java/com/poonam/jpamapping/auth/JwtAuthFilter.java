package com.poonam.jpamapping.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Step 1: Header se pass nikalo
        String header = request.getHeader("Authorization");

        // Step 2: Header hai aur "Bearer " se shuru hota hai?
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            // Step 3: Token sahi hai?
            if (jwtService.isTokenValid(token)) {
                String username = jwtService.extractUsername(token);
                String role = jwtService.extractRole(token);

                // Step 4: Spring ko batao "ye banda andar hai"
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role)));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Step 5: Request ko aage jaane do
        filterChain.doFilter(request, response);
    }
}