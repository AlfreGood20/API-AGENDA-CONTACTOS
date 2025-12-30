package com.api.api_contacto.configuracion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.api.api_contacto.exepciones.ExepcionAutenticacion;
import com.api.api_contacto.exepciones.ExepcionAutorizacion;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private ExepcionAutenticacion exepcionAutenticacion;

    @Autowired
    private ExepcionAutorizacion exepcionAutorizacion;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filtroSeguridad(HttpSecurity http) throws Exception{
        return http 
                .csrf((csrf) -> csrf.disable())

                .authorizeHttpRequests((authorizeHttpRequest) -> authorizeHttpRequest
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/user/**").hasAnyRole("USER","ADMIN")
                    .requestMatchers(
                        "/api/auth/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/proxy/**",
                        "/actuator/**")
                        .permitAll()
                    .anyRequest().permitAll()
                )

                .exceptionHandling((exeption) -> exeption
                    .authenticationEntryPoint(exepcionAutenticacion)
                    .accessDeniedHandler(exepcionAutorizacion)
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .httpBasic((httpBasic) -> httpBasic
                    .disable())
                
                .build();
    }
}