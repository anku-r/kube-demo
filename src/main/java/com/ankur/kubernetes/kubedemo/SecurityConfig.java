package com.ankur.kubernetes.kubedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableWebSecurity
public class SecurityConfig  {

    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ankur =
                User.withDefaultPasswordEncoder()
                        .username("ankur")
                        .password("abcd1234")
                        .roles("USER")
                        .build();

        UserDetails nginx =
                User.withDefaultPasswordEncoder()
                        .username("nginx")
                        .password("abcd1234")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("abcd1234")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(ankur, admin, nginx);
    }
}
