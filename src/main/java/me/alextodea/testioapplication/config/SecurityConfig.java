package me.alextodea.testioapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final LogoutHandler logoutHandler;


    public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/", "/images/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).addLogoutHandler(logoutHandler);

        return http.build();
    }
}
