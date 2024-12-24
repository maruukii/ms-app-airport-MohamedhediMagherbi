package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.entities.FlightLeg;
import com.mohamedhedimagherbi.flightservice.model.Destination;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceFlightLeg {
    public List<FlightLeg> AllFlightsDestinations();
    public FlightLeg assignDestinationToFlight(int flightId, int destinationId, LocalDateTime stop_date,LocalDateTime back_to_flight_date);
    public List<Destination> getAllFlightDestinations(int flightId);
}
