package com.example.Ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.Ecommerce.user.Permission.*;
import static com.example.Ecommerce.user.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/ecommerce/v1/auth/**","/ecommerce/v1/**","/api/users/**","/api/Address/**")
                                .permitAll()
                                .requestMatchers("/ecommerce/v1/**").hasAnyRole(ADMIN.name(), SELLER.name(),CUSTOMER.name())
                                .requestMatchers(GET, "/ecommerce/v1/**").hasAnyAuthority(ADMIN_READ.getPermission(),SELLER_READ.getPermission(), CUSTOMER_READ.getPermission())
                                .requestMatchers(POST, "/ecommerce/v1/**").hasAnyAuthority(ADMIN_CREATE.getPermission(), SELLER_CREATE.getPermission(), CUSTOMER_CREATE.getPermission())
                                .requestMatchers(PUT, "/ecommerce/v1/**").hasAnyAuthority(ADMIN_UPDATE.getPermission(), SELLER_UPDATE.getPermission(), CUSTOMER_UPDATE.getPermission())
                                .requestMatchers(DELETE, "/ecommerce/v1/**").hasAnyAuthority(ADMIN_DELETE.getPermission(), SELLER_DELETE.getPermission(), CUSTOMER_DELETE.getPermission())


                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
