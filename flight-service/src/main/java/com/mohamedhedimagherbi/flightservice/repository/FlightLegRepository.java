package com.mohamedhedimagherbi.flightservice.repository;

import com.mohamedhedimagherbi.flightservice.entities.FlightLeg;
import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightLegRepository extends JpaRepository<FlightLeg,Integer> {
    public List<FlightLeg> findAllByFlightId(int flightid);
}
