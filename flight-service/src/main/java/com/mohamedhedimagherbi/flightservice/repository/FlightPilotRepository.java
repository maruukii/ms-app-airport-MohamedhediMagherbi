package com.mohamedhedimagherbi.flightservice.repository;

import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightPilotRepository extends JpaRepository<FlightPilot,Integer>{
//    public List<FlightPilot> findAllByFlightId();
}
