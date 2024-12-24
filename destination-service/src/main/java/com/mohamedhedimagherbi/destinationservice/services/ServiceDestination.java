package com.mohamedhedimagherbi.destinationservice.services;

import com.mohamedhedimagherbi.destinationservice.entities.Destination;
import com.mohamedhedimagherbi.destinationservice.repository.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceDestination implements IServiceDestination {
    private DestinationRepository destinationRepository;
    public Optional<Destination> getById(int id){
        return destinationRepository.findById(id);
    }
    public Destination addDestination(Destination destination){
        destination.setCreated_At(LocalDateTime.now());
        destination.setModified_At(LocalDateTime.now());
       return destinationRepository.save(destination);
    }
    public List<Destination> getAllDestinations(){
        return destinationRepository.findAll();
    }

    public void deleteDestinationById(int id) {
        Optional<Destination> existingDestination= destinationRepository.findById(id);
        if (existingDestination.isPresent())
            destinationRepository.deleteById(id);
        else
            throw new RuntimeException("Destination not found with id: " + id);
    }
    public Destination updateDestination(int id,Destination updatedDestination) {
        Optional<Destination> existingDestination = destinationRepository.findById(id);

        if (existingDestination.isPresent()) {
            Destination pilot = existingDestination.get();
            if (updatedDestination.getAirport_name()!=null)
            pilot.setAirport_name(updatedDestination.getAirport_name());
            if(updatedDestination.getCity_name()!=null)
            pilot.setCity_name(updatedDestination.getCity_name());
            pilot.setModified_At(LocalDateTime.now());
            return destinationRepository.save(pilot);
        } else {
            throw new RuntimeException("Destination not found with id: " + id);
        }
    }

}
