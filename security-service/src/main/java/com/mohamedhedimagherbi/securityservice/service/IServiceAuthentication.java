package com.mohamedhedimagherbi.securityservice.service;

import com.mohamedhedimagherbi.securityservice.entities.AppRole;
import com.mohamedhedimagherbi.securityservice.entities.AppUser;

public interface IServiceAuthentication {
    public AppUser createAppUser (AppUser appUser);
    public AppRole createAppRole (AppRole appRole);
    public void addRoleToUser(String username, String role);
    public AppUser LoadUserByUserName (String username);
}
