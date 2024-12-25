package com.mohamedhedimagherbi.securityservice.service;

import com.mohamedhedimagherbi.securityservice.entities.AppRole;
import com.mohamedhedimagherbi.securityservice.entities.AppUser;
import com.mohamedhedimagherbi.securityservice.repositories.AppRoleRepository;
import com.mohamedhedimagherbi.securityservice.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceAuthentication implements IServiceAuthentication{
private AppUserRepository AppUserRepository;
private AppRoleRepository AppRoleRepository;
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    public AppUser LoadUserByUserName(String username) {
        return AppUserRepository.findByUsername(username);
    }
    @Override
    public AppUser createAppUser(AppUser appUser) {
        AppUser found = LoadUserByUserName(appUser.getUsername());
        if(found==null){
            appUser.setPassword(passwordEncoder().encode(appUser.getPassword()));
            return AppUserRepository.save(appUser);
        }
        return null;
    }
    @Override
    public AppRole createAppRole(AppRole appRole) {
        AppRole found = AppRoleRepository.findByRole(appRole.getRole());
        if(found==null){
            return AppRoleRepository.save(appRole);
        }
        return null;
    }
    @Override
    public void addRoleToUser(String username, String role) {
        AppUser foundUser = LoadUserByUserName(username);
        if(foundUser!=null){
            AppRole foundRole=AppRoleRepository.findByRole(role);
            if(foundRole!=null){
                if(foundUser.getRoles().contains(foundRole)==false){
                foundUser.getRoles().add(foundRole);
                AppUserRepository.save(foundUser);}
            }
        }
    }
}
