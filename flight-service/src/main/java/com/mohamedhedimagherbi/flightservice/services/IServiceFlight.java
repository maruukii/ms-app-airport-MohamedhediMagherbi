package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.entities.Flight;

import java.util.List;
import java.util.Optional;

public interface IServiceFlight {
    public Optional<Flight> getById(int id);
    public Flight addFlight(Flight flight);
    public List<Flight> getAllFlights();
    public void deleteFlightById(int id);
    public Flight updateFlight(int id,Flight updatedFlight);
//    public List<Passenger> getPassengersByFlight(int id);
}
