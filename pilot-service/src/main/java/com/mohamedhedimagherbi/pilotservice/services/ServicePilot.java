package com.mohamedhedimagherbi.pilotservice.services;

import com.mohamedhedimagherbi.pilotservice.entities.Pilot;
import com.mohamedhedimagherbi.pilotservice.repository.PilotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicePilot implements IServicePilot {
    private PilotRepository pilotRepository;
    public Optional<Pilot> getById(int id){
        return pilotRepository.findById(id);
    }
    public Pilot addPilot(Pilot pilot){
        pilot.setCreated_At(LocalDateTime.now());
        pilot.setModified_At(LocalDateTime.now());
       return pilotRepository.save(pilot);
    }
    public List<Pilot> getAllPilots(){
        return pilotRepository.findAll();
    }

    public void deletePilotById(int id) {
        Optional<Pilot> existingPilot= pilotRepository.findById(id);
        if (existingPilot.isPresent())
            pilotRepository.deleteById(id);

        else
            throw new RuntimeException("Pilot not found with id: " + id);
    }
    public Pilot updatePilot(int id,Pilot updatedPilot) {
        Optional<Pilot> existingPilot = pilotRepository.findById(id);

        if (existingPilot.isPresent()) {
            Pilot pilot = existingPilot.get();
            if (updatedPilot.getFirst_name()!=null)
            pilot.setFirst_name(updatedPilot.getFirst_name());
            if(updatedPilot.getLast_name()!=null)
            pilot.setLast_name(updatedPilot.getLast_name());
            pilot.setModified_At(LocalDateTime.now());
            if(updatedPilot.getJob_title()!=null)
            pilot.setJob_title(updatedPilot.getJob_title());
            if(updatedPilot.getDate_birth()!=null)
            pilot.setDate_birth(updatedPilot.getDate_birth());
            if(updatedPilot.getEmail()!=null)
            pilot.setEmail(updatedPilot.getEmail());
            if(updatedPilot.getPhone_number()!=0)
            pilot.setPhone_number(updatedPilot.getPhone_number());
            return pilotRepository.save(pilot);
        } else {
            throw new RuntimeException("Pilot not found with id: " + id);
        }
    }

}
