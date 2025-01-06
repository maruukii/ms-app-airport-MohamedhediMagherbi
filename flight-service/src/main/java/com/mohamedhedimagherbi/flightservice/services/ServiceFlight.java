package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.clients.DestinationRestClient;
import com.mohamedhedimagherbi.flightservice.clients.PilotRestClient;
import com.mohamedhedimagherbi.flightservice.clients.PlaneRestClient;
import com.mohamedhedimagherbi.flightservice.entities.Flight;
import com.mohamedhedimagherbi.flightservice.entities.FlightLeg;
import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import com.mohamedhedimagherbi.flightservice.model.Pilot;
import com.mohamedhedimagherbi.flightservice.model.Plane;
import com.mohamedhedimagherbi.flightservice.repository.FlightLegRepository;
import com.mohamedhedimagherbi.flightservice.repository.FlightPilotRepository;
import com.mohamedhedimagherbi.flightservice.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceFlight implements IServiceFlight {
    private FlightRepository flightRepository;
    private PlaneRestClient planeRestClient;
    private PilotRestClient pilotRestClient;
    private DestinationRestClient destinationRestClient;
    private FlightPilotRepository flightPilotRepository;
    private FlightLegRepository flightLegRepository;
    public Optional<Flight> getById(int id){
        return flightRepository.findById(id);
    }
    public Flight addFlight(Flight flight){
        Plane plane = planeRestClient.getPlaneById(flight.getPlaneId());
        if (plane==null) throw new RuntimeException("No plane found with id: "+flight.getPlaneId());
        flight.setCreated_At(LocalDateTime.now());
        flight.setModified_At(LocalDateTime.now());
       return flightRepository.save(flight);
    }
    public List<Flight> getAllFlights(){

        List<Flight> flights = flightRepository.findAll();
        List<String> pilots;
        List<String> destinations;
        for (Flight flight:flights
             ) {
            flight.setPlane(planeRestClient.getPlaneById(flight.getPlaneId()));
            pilots=new ArrayList<>();
            List<FlightPilot> flightPilot=flightPilotRepository.findAllByFlightId(flight.getId());
            for (FlightPilot flightpilot: flightPilot
                 ) {

                pilots.add(pilotRestClient.getPilotById(flightpilot.getPilotId()).getFirst_name()+" "+pilotRestClient.getPilotById(flightpilot.getPilotId()).getLast_name());
            }
            flight.setPilots(pilots);
            destinations=new ArrayList<>();
            List<FlightLeg> flightLeg=flightLegRepository.findAllByFlightId(flight.getId());
            for (FlightLeg flightleg:flightLeg
                 ) {
                destinations.add(destinationRestClient.getDestinationById(flightleg.getDestinationId()).getAirport_name()+" - "+destinationRestClient.getDestinationById(flightleg.getDestinationId()).getCity_name());
            }
            flight.setDestinations(destinations);
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
