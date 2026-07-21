
package com.kalaivannamhub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/api/auth/**").permitAll()

            	    // Public APIs
            	    .requestMatchers(HttpMethod.GET, "/api/artworks/**").permitAll()

            	    // Customer APIs
            	    .requestMatchers("/api/customer/**").hasRole("CUSTOMER")

            	    // Artist APIs
            	    .requestMatchers("/api/artist/**").hasRole("ARTIST")

            	    // Admin APIs
            	    .requestMatchers("/api/admin/**").hasRole("ADMIN")

            	    // Add/Edit/Delete Artwork - Artist only
            	    .requestMatchers(HttpMethod.POST, "/api/artworks/**").authenticated()
            	    .requestMatchers(HttpMethod.PUT, "/api/artworks/**").hasRole("ARTIST")
            	    .requestMatchers(HttpMethod.DELETE, "/api/artworks/**").hasRole("ARTIST")

            	    .anyRequest().authenticated()
            	)
            .addFilterBefore(jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
