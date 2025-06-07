package com.example.BE_Library.common.configuration.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

//    @Bean
//    @Order(1)
//    public SecurityFilterChain configureSwaggerSecurity(HttpSecurity http) throws Exception {
//        return http
//                .securityMatcher(AntPathRequestMatcher.antMatcher("/swagger-ui/**"))
//                .securityMatcher(AntPathRequestMatcher.antMatcher("/v3/api-docs/**"))
//                .securityMatcher(AntPathRequestMatcher.antMatcher("/api-doc/**"))
//                .httpBasic(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
//                .build();
//    }

//    private static Customizer<CorsConfigurer<HttpSecurity>> configureCors() {
//        return cors -> cors.configurationSource(request -> {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(List.of("*"));
//            configuration.setAllowedMethods(List.of("*"));
//            configuration.setAllowedHeaders(List.of("*"));
//            return configuration;
//        });
//    }
}
