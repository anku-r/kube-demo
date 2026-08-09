package com.ankur.kubernetes.kubedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails ankur =
                User.withUsername("ankur")
                        .password(passwordEncoder.encode("abcd1234"))
                        .roles("USER")
                        .build();

        UserDetails nginx =
                User.withUsername("nginx")
                        .password(passwordEncoder.encode("abcd1234"))
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoder.encode("abcd1234"))
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(ankur, admin, nginx);
    }
}
