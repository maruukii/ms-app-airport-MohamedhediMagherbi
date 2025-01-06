package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.clients.PilotRestClient;
import com.mohamedhedimagherbi.flightservice.clients.PlaneRestClient;
import com.mohamedhedimagherbi.flightservice.entities.Flight;
import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import com.mohamedhedimagherbi.flightservice.clients.PilotRestClient;
import com.mohamedhedimagherbi.flightservice.model.Pilot;
import com.mohamedhedimagherbi.flightservice.repository.FlightPilotRepository;
import com.mohamedhedimagherbi.flightservice.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceFlightPilot implements IServiceFlightPilot {
    private FlightPilotRepository flightPilotRepository;
    private FlightRepository flightRepository;
    private PilotRestClient pilotRestClient;
    private PlaneRestClient planeRestClient;

    @Override
    public List<FlightPilot> AllFlightsPilots() {
        List<FlightPilot> allFlights=flightPilotRepository.findAll();
        for (FlightPilot flightpilot:allFlights
             ) {
            Optional<Flight> flight =flightRepository.findById(flightpilot.getFlightId());
            flight.get().setPlane(planeRestClient.getPlaneById(flight.get().getPlaneId()));
            flightpilot.setFlight(flight);
            flightpilot.setPilot(pilotRestClient.getPilotById(flightpilot.getPilotId()));
        }
        return allFlights;
    }

    @Override
    public FlightPilot assignPilotToFlight(int flightId,int pilotId,String role) {
        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        if (existingFlight.isPresent()) {
            List<FlightPilot> pilotsflights =flightPilotRepository.findAllByFlightIdAndAndPilotId(flightId,pilotId);
                if (pilotsflights.size() <= 3) {
                    for (FlightPilot checkpilot:pilotsflights
                    ) {
                        if(checkpilot.getPilotId()==pilotId) throw new RuntimeException("Pilot already exists in this flight");
                    }
                    Pilot pilot = pilotRestClient.getPilotById(pilotId);
                    FlightPilot flightPilot = new FlightPilot();
                    if (pilot != null) {
                        flightPilot.setFlightId(flightId);
                        flightPilot.setPilotId(pilotId);
                        flightPilot.setCockpit_role(role);
                        return flightPilotRepository.save(flightPilot);
                    }
                    throw new RuntimeException("Pilot doesn't exist");
                }
                throw new RuntimeException("Already set 4 pilots for the flight: " + flightId);

        }
        throw new RuntimeException("no flight found with Flight id: " + flightId);
    }

    @Override
    public List<Pilot> getAllFlightPilots(int flightId) {
        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        if (existingFlight.isPresent()){
            List<FlightPilot> flightPilot= flightPilotRepository.findAllByFlightId(flightId);
            List <Pilot> pilots=new ArrayList<>();;
            for (FlightPilot flightpilot:flightPilot) {

            Pilot pilot = pilotRestClient.getPilotById(flightpilot.getPilotId());
            if (pilot != null) {
            pilots.add(pilot);}
            }
            return pilots;
        }
        throw new RuntimeException("no pilots found for Flight id: " + flightId);
    }


}
