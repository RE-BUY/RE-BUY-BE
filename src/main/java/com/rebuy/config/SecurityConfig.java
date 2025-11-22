package com.rebuy.config;

import com.rebuy.security.CustomUserDetailsService;
import com.rebuy.security.JwtAuthenticationFilter;
import com.rebuy.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 주입 (로그인 시 사용)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService);

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Swagger & Setup 허용
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/setup**").permitAll()

                        // 2. 로그인 관련 허용
                        .requestMatchers("/api/v1/auth/**", "/api/v1/users/**").permitAll()

                        // ★ 3. 상품 조회(GET)는 누구나 허용! (이 줄 추가)
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()

                        // 4. 나머지는 인증 필요 (상품 등록 등)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}