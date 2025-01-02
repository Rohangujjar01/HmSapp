package com.hmsapp.hmsapp.config;

import com.hmsapp.hmsapp.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        //HC2
        http.cors().and().csrf().disable();
        //THIS line is to filter the url which has only tokens in them and doesn't send the url which the token is not present
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
        //HAAP
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/auth/sign-up","/api/auth/login","/api/auth/property/sign-up")
//                .permitAll()
//                .requestMatchers("/api/v1/property/add")
//                .hasRole("PROPERTYOWNER")
//                .requestMatchers("/api/v1/property/delete")
//                .hasAnyRole("PROPERTYOWNER","ADMIN")
//                .requestMatchers("/api/auth/blog/sign-up")
//                .hasRole("ADMIN")
//                .anyRequest().authenticated();
       return http.build();
    }
}
