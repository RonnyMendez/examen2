package org.examen.alquiler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConifig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler(successHandler())
                    .permitAll()
                .and()
                .csrf().disable()
                .rememberMe(Customizer.withDefaults());
        return http.build();
    }

    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            response.sendRedirect("/");
        });
    }
}
