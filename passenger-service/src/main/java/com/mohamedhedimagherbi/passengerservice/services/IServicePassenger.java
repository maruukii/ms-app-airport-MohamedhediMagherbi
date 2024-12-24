package com.mohamedhedimagherbi.passengerservice.services;

import com.mohamedhedimagherbi.passengerservice.entities.Passenger;

import java.util.List;
import java.util.Optional;

public interface IServicePassenger {
    public Optional<Passenger> getById(int id);
    public Passenger addPassenger(Passenger passenger);
    public List<Passenger> getAllPassengers();
    public void deletePassengerById(int id);
    public Passenger updatePassenger(int id,Passenger updatedPassenger);
    public List<Passenger> PassengersByFlight(int id);
}
