package com.mohamedhedimagherbi.pilotservice.services;

import com.mohamedhedimagherbi.pilotservice.entities.Pilot;

import java.util.List;
import java.util.Optional;

public interface IServicePilot {
    public Optional<Pilot> getById(int id);
    public Pilot addPilot(Pilot plane);
    public List<Pilot> getAllPilots();
    public void deletePilotById(int id);
    public Pilot updatePilot(int id,Pilot updatedPilot);
}
