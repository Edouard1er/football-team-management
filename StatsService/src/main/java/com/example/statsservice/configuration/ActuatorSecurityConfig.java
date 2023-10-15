package com.example.statsservice.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Autorise l'accès à l'endpoint Shutdown uniquement aux utilisateurs avec le rôle "ACTUATOR_ADMIN".
                .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
                .hasRole("ACTUATOR_ADMIN")
                // Autorise l'accès à tous les autres endpoints Actuator (health, metrics, etc.) sans authentification.
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                // Autorise l'accès aux ressources statiques (par exemple, les fichiers CSS) sans authentification.
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                // Autorise l'accès aux URL de base ("/" et "/slowApi") sans authentification.
                .antMatchers("/", "/slowApi")
                .permitAll()
                // Exige l'authentification pour toutes les autres URL (par exemple, les endpoints personnalisés de votre application).
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic(); // Active l'authentification HTTP de base.
    }
}
