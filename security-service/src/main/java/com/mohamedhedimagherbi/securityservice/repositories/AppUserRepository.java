package com.mohamedhedimagherbi.securityservice.repositories;

import com.mohamedhedimagherbi.securityservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    public AppUser findByUsername(String Username);
}
