package com.mohamedhedimagherbi.securityservice.repositories;

import com.mohamedhedimagherbi.securityservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Integer> {
    public AppRole findByRole(String Role);
}
