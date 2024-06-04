package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @SuppressWarnings("deprecation")
	@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpass")
                .roles("ADMIN").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("userpass")
                .roles("USER").build());
        return manager;
    }

    @SuppressWarnings("deprecation")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests(authorizeRequests ->
        authorizeRequests
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("USER")
            .requestMatchers("/login").permitAll()
            .anyRequest().authenticated()
    )
    .formLogin(formLogin ->
        formLogin
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
    )
    .logout(logout ->
        logout
            .permitAll()
    );
return http.build();
}
}

  