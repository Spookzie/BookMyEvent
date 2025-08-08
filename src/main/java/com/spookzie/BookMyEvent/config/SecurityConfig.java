package com.spookzie.BookMyEvent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import com.spookzie.BookMyEvent.filters.UserProvisioningFilter;


@Configuration
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserProvisioningFilter userProvisioningFilter) throws Exception
    {
        http
                .authorizeHttpRequests(
                        authorize -> authorize.anyRequest().authenticated()
                ).csrf(
                        csrf -> csrf.disable()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(Customizer.withDefaults()) // Setting up JWT token validation using default settings
                ).addFilterAfter(
                        userProvisioningFilter, BearerTokenAuthenticationFilter.class
                );

        return http.build();
    }
}