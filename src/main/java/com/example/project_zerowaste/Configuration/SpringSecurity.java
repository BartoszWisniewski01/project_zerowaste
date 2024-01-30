package com.example.project_zerowaste.Configuration;

import com.example.project_zerowaste.Services.ApplicationUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.project_zerowaste.Services.UserService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SpringSecurity {
    private ApplicationUserDetailsService userDetailsService;
    private UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests((authorize) ->
                        authorize.requestMatchers("/**","/register", "/register/**", "/login","/login/**").permitAll()
                                .requestMatchers("/products/all", "/packages/all", "/orders/all","/notifications/all","/mainpage").access("hasAnyRole('USER', 'ADMIN', 'SELLER') and @userService.checkUserStatus(authentication)")
                                .requestMatchers("/products/**", "/packages/**").access("hasAnyRole('SELLER') and @userService.checkUserStatus(authentication)")
                                .requestMatchers("/product-review/**", "/seller-review/**", "/orders/**", "/tickets/*/add" ).access("hasAnyRole('USER') and @userService.checkUserStatus(authentication)")
                                .requestMatchers("/admin", "/admin/**","/tickets/**").access("hasAnyRole('ADMIN') and @userService.checkUserStatus(authentication)")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/notifications/all")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                )
                .headers(headers -> headers.frameOptions().disable());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}