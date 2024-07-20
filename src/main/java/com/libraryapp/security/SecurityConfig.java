package com.libraryapp.security;

import com.libraryapp.security.filter.AuthenticationFilter;
import com.libraryapp.security.filter.JWTAuthorizationFilter;
import com.libraryapp.security.manager.CustomAuthenticationManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_PATH);

        return httpSecurity
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(
                auth -> auth.requestMatchers(HttpMethod.POST, SecurityConstants.REGISTRATION_PATH).permitAll()
                    .requestMatchers(SecurityConstants.LOGIN_PAGE).permitAll()
                    .requestMatchers(SecurityConstants.HOME_PAGE).permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage(SecurityConstants.LOGIN_PAGE)
                .permitAll()
            )
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/") {
            @Override
            public void onAuthenticationSuccess(
                HttpServletRequest request,
                HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
                // Set the response status to 200 OK
                response.setStatus(HttpServletResponse.SC_OK);
                // Redirect to homepage
                getRedirectStrategy().sendRedirect(request, response, "/");
            }
        };
    }
}
