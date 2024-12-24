package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.clients.PlaneRestClient;
import com.mohamedhedimagherbi.flightservice.entities.Flight;
import com.mohamedhedimagherbi.flightservice.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceFlight implements IServiceFlight {
    private FlightRepository flightRepository;
    private PlaneRestClient planeRestClient;
    public Optional<Flight> getById(int id){
        return flightRepository.findById(id);
    }
    public Flight addFlight(Flight flight){
        flight.setCreated_At(LocalDateTime.now());
        flight.setModified_At(LocalDateTime.now());
       return flightRepository.save(flight);
    }
    public List<Flight> getAllFlights(){

        List<Flight> flights = flightRepository.findAll();
        for (Flight flight:flights
             ) {
            flight.setPlane(planeRestClient.getPlaneById(flight.getPlaneId()));
        }
        return flights;
    }

    @Override
    public void deleteFlightById(int id) {
        Optional<Flight> existingPassenger = flightRepository.findById(id);
        if (existingPassenger.isPresent())
            flightRepository.deleteById(id);
        else
            throw new RuntimeException("Flight not found with id: " + id);
    }


    public Flight updateFlight(int id,Flight updatedFlight) {
        Optional<Flight> existingPassenger = flightRepository.findById(id);

        if (existingPassenger.isPresent()) {
            Flight flight = existingPassenger.get();
            if(updatedFlight.getFlight_name()!=null)
            flight.setFlight_name(updatedFlight.getFlight_name());
            if(updatedFlight.getFlight_type()!=null)
            flight.setFlight_type(updatedFlight.getFlight_type());
            if(updatedFlight.getArrival_date()!=null)
            flight.setArrival_date(updatedFlight.getArrival_date());
            if(updatedFlight.getDeparture_date()!=null)
            flight.setDeparture_date(updatedFlight.getDeparture_date());
            if(updatedFlight.getPlane()!=null)
            flight.setPlane(updatedFlight.getPlane());
            if(updatedFlight.getPlaneId()!=0)
            flight.setPlaneId(updatedFlight.getPlaneId());
            flight.setModified_At(LocalDateTime.now());

            return flightRepository.save(flight);
        } else {
            throw new RuntimeException("Flight not found with id: " + id);
        }
    }

}
