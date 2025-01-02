package com.hmsapp.hmsapp.config;

import com.hmsapp.hmsapp.Entity.User;
import com.hmsapp.hmsapp.repository.UserRepository;
import com.hmsapp.hmsapp.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private final UserRepository userRepository;

    public JWTFilter(JWTService jwtService,
                     UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(8, token.length() -1);
            String username = jwtService.getUsername(jwtToken);
            Optional<User> opUsername = userRepository.findByUsername(username);

            if (opUsername.isPresent()) {
                User user = opUsername.get();
                UsernamePasswordAuthenticationToken
                        userToken = new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
                userToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
