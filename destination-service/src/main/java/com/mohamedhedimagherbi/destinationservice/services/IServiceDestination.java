package com.mohamedhedimagherbi.destinationservice.services;

import com.mohamedhedimagherbi.destinationservice.entities.Destination;

import java.util.List;
import java.util.Optional;

public interface IServiceDestination {
    public Optional<Destination> getById(int id);
    public Destination addDestination(Destination destination);
    public List<Destination> getAllDestinations();
    public void deleteDestinationById(int id);
    public Destination updateDestination(int id,Destination updatedDestination);
}
