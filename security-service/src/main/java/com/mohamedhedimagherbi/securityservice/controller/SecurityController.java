package com.mohamedhedimagherbi.securityservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    @GetMapping("/profile")
    public Authentication profile(Authentication authentication){
    return authentication;
    }
    @PostMapping("/login")
    public Map<String,String> login(String username,String password){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        String scope=authentication.getAuthorities ().stream().map(a->a.getAuthority()).collect (Collectors.joining(" "));
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt (instant.plus (1, ChronoUnit.HOURS))
                .subject (username)
                .claim("scope", scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with (MacAlgorithm. HS512).build(),
                jwtClaimsSet
        );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("access-token",jwt);
    }

}
