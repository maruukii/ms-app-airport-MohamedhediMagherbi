package com.mohamedhedimagherbi.securityservice.configuration;

import com.mohamedhedimagherbi.securityservice.service.UserDetailsServiceImpl;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration {
    @Value("${jwt-secret}")
    private String secretKey;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public JwtEncoder jwtEncoder(){
return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec (secretKey.getBytes(), "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm (MacAlgorithm. HS512).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager (UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder (passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService (userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);}
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .sessionManagement (sm->sm.sessionCreationPolicy (SessionCreationPolicy.STATELESS))
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests (auth->auth.requestMatchers("/api/auth/addUser","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**","/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/addRole").hasAuthority("SCOPE_ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/auth/grantRole").hasAuthority("SCOPE_ROLE_ADMIN")
                    .anyRequest().authenticated())
                .oauth2ResourceServer (oa -> oa.jwt (Customizer.withDefaults())).userDetailsService(userDetailsServiceImpl).build();
    }

}
