package com.mohamedhedimagherbi.passengerservice.repository;

import com.mohamedhedimagherbi.passengerservice.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
 public List<Passenger> findAllByFlightId(int id);
}