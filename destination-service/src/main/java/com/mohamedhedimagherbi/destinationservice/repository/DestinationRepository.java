package com.mohamedhedimagherbi.destinationservice.repository;

import com.mohamedhedimagherbi.destinationservice.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination,Integer> {

}