package com.mohamedhedimagherbi.pilotservice.controller;

import com.mohamedhedimagherbi.pilotservice.entities.Pilot;
import com.mohamedhedimagherbi.pilotservice.services.IServicePilot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pilot/")
public class PilotRestController {
    private IServicePilot IServicePilot;
    @GetMapping("{id}")
    public Optional<Pilot> getById(@PathVariable int id){
        return IServicePilot.getById(id);
    }
    @PostMapping("add")
    //@PreAuthorize("hasAuthority('ADMIN')")

    public Pilot add(@RequestBody Pilot pilot){
        return IServicePilot.addPilot(pilot);
    }
    @GetMapping("all")
    public List<Pilot> allPilots(){
        return IServicePilot.getAllPilots();
    }
    @PutMapping("update/{id}")
    public Pilot updatePilot(@PathVariable int id,@RequestBody Pilot updatedPilot){
        return IServicePilot.updatePilot(id,updatedPilot);
    }
    @PostMapping("delete/{id}")
    public void deletePilot(@PathVariable int id){
         IServicePilot.deletePilotById(id);
    }
}
