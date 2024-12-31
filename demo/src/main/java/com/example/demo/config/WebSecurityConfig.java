package com.example.demo.config;

import com.example.demo.filter.JwtAuthenticationFilter;
import com.example.demo.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(
    final HttpSecurity http
  ) throws Exception {
    http.authorizeHttpRequests(auth -> auth
      .requestMatchers(
        "/api/login",
        "/api-docs/**", "/swagger-ui/**"    // swagger表示用であるため制限なし
      ).permitAll()      // 制限なし
      .requestMatchers("/api/v1/**").authenticated()  // 要ログイン
    );
    //).csrf().disable();   // これがあると/api/loginが通らない
    http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
    http.addFilter(
      new JwtAuthenticationFilter(
        authenticationManager(
          http.getSharedObject(AuthenticationConfiguration.class)
        )
      )
    );
    http.addFilterAfter(new LoginFilter(), JwtAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    final AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedOrigins(List.of("http://localhost:5173/"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    configuration.addExposedHeader("X-AUTH-TOKEN");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
