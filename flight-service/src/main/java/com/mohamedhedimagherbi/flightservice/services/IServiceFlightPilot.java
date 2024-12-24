package com.mohamedhedimagherbi.flightservice.services;

import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import com.mohamedhedimagherbi.flightservice.model.Pilot;

import java.util.List;
import java.util.Optional;

public interface IServiceFlightPilot {
    public List<FlightPilot> AllFlightsPilots();
    public FlightPilot assignPilotToFlight(int flightId, int pilotId,String role);
    public List<Pilot> getAllFlightPilots(int flightId);
}
