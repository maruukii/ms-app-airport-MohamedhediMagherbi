package com.mohamedhedimagherbi.flightservice.repository;

import com.mohamedhedimagherbi.flightservice.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Integer> {

}