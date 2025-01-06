package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.clients.DestinationRestClient;
import com.mohamedhedimagherbi.flightservice.clients.PilotRestClient;
import com.mohamedhedimagherbi.flightservice.clients.PlaneRestClient;
import com.mohamedhedimagherbi.flightservice.entities.Flight;
import com.mohamedhedimagherbi.flightservice.entities.FlightLeg;
import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import com.mohamedhedimagherbi.flightservice.model.Destination;
import com.mohamedhedimagherbi.flightservice.model.Pilot;
import com.mohamedhedimagherbi.flightservice.repository.FlightLegRepository;
import com.mohamedhedimagherbi.flightservice.repository.FlightPilotRepository;
import com.mohamedhedimagherbi.flightservice.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceFlightLeg implements IServiceFlightLeg {
    private FlightLegRepository flightLegRepository;
    private FlightRepository flightRepository;
    private DestinationRestClient destinationRestClient;
    private PlaneRestClient planeRestClient;

    @Override
    public List<FlightLeg> AllFlightsDestinations() {
        List<FlightLeg> allFlights=flightLegRepository.findAll();
        for (FlightLeg flightleg:allFlights
        ) {
            Optional<Flight> flight =flightRepository.findById(flightleg.getFlightId());
            flight.get().setPlane(planeRestClient.getPlaneById(flight.get().getPlaneId()));
            flightleg.setFlight(flight);
            flightleg.setDestination(destinationRestClient.getDestinationById(flightleg.getDestinationId()));
        }
        return allFlights;
    }

    @Override
    public FlightLeg assignDestinationToFlight(int flightId, int destinationId, LocalDateTime stop_date, LocalDateTime back_to_flight_date) {
        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        if (existingFlight.isPresent()){
            FlightLeg flightLeg = new FlightLeg();
            Destination destination = destinationRestClient.getDestinationById(destinationId);
            if (destination != null) {
                flightLeg.setFlightId(flightId);
                flightLeg.setDestinationId(destinationId);
                flightLeg.setStop_date(stop_date);
                flightLeg.setBack_to_flight_date(back_to_flight_date);
                return flightLegRepository.save(flightLeg);
            }
            throw new RuntimeException("no destination found with destination id: " + destinationId);
        }
        throw new RuntimeException("no flight found with Flight id: " + flightId);
    }

    @Override
    public List<Destination> getAllFlightDestinations(int flightId) {
        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        if (existingFlight.isPresent()){
            List<FlightLeg> flightLegs= flightLegRepository.findAllByFlightId(flightId);
            List <Destination> destinations=new ArrayList<>();;
            for (FlightLeg flightLeg:flightLegs) {
                Destination destination = destinationRestClient.getDestinationById(flightLeg.getDestinationId());
                if (destination != null) {
                    destinations.add(destination);}
            }
            return destinations;
        }
        throw new RuntimeException("no destinations found with flight id: " + flightId);
    }


}
