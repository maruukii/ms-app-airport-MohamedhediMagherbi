package com.mohamedhedimagherbi.securityservice.controller;

import com.mohamedhedimagherbi.securityservice.entities.AppRole;
import com.mohamedhedimagherbi.securityservice.entities.AppUser;
import com.mohamedhedimagherbi.securityservice.service.IServiceAuthentication;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthenticationController {
    private IServiceAuthentication iServiceAuthentication;
    @PostMapping("addUser")
    public AppUser addUser(@RequestBody AppUser appUser){
        return iServiceAuthentication.createAppUser(appUser);
    }
    @PostMapping("addRole")
    public AppRole addRole(@RequestBody AppRole appRole){
        return iServiceAuthentication.createAppRole(appRole);
    }
    @PutMapping("grantRole")
    public void grantRole(@RequestParam String username,@RequestParam String role){
        iServiceAuthentication.addRoleToUser(username,role);
    }

}
