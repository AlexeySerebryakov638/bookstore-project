package com.bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll())
        	.authorizeHttpRequests(auth -> auth.requestMatchers("/**.jpg").permitAll())
			.authorizeHttpRequests(auth -> auth.requestMatchers("/book").permitAll())
    		.authorizeHttpRequests(auth -> auth.requestMatchers("/user/**").permitAll())
        	.authorizeHttpRequests(auth -> auth.requestMatchers("/cart/**").authenticated())
        	.authorizeHttpRequests(auth -> auth.requestMatchers("/orders/**").authenticated())
        	.authorizeHttpRequests(auth -> auth.requestMatchers("/buy").authenticated())
        	.authorizeHttpRequests(auth -> auth.requestMatchers("/employee/**").hasAnyAuthority("employee", "admin"))
        	.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").hasAnyAuthority("employee", "admin"))
            .formLogin(login -> login.permitAll())
            .logout(logout -> logout.permitAll());
         
        return http.build();
    }
}
