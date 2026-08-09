package com.ankur.kubernetes.kubedemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder,
            @Value("${security.users:}") String securityUsers) {
        if (!StringUtils.hasText(securityUsers)) {
            throw new IllegalStateException("No security.users value available from Kubernetes Secret or application properties");
        }

        List<UserDetails> users = Arrays.stream(securityUsers.split(","))
                .map(String::trim)
                .filter(StringUtils::hasText)
                .map(entry -> {
                    String[] parts = entry.split(":");
                    if (parts.length < 3) {
                        throw new IllegalArgumentException(
                                "security.users entry must be username:password:ROLE[,ROLE] formatted");
                    }
                    String username = parts[0].trim();
                    String password = parts[1];
                    String[] roles = Arrays.copyOfRange(parts, 2, parts.length);
                    return User.withUsername(username)
                            .password(passwordEncoder.encode(password))
                            .roles(roles)
                            .build();
                })
                .collect(Collectors.toList());

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/health/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
