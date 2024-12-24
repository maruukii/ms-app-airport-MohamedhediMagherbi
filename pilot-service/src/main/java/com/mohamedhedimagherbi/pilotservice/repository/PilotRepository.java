package com.mohamedhedimagherbi.pilotservice.repository;

import com.mohamedhedimagherbi.pilotservice.entities.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot,Integer> {

}