package com.vti.config;

import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // phương phasp hóa password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "api/v1/accounts/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "api/v1/accounts/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/accounts/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()// các api còn lại thì ko cần xét quyền
                )
                .httpBasic(Customizer.withDefaults());// dùng basic authen
        return http.build();
    }
}