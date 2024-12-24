package com.mohamedhedimagherbi.flightservice.repository;

import com.mohamedhedimagherbi.flightservice.entities.FlightLeg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightLegRepository extends JpaRepository<FlightLeg,Integer> {
}
