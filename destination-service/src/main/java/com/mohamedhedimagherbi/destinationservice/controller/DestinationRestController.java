package com.mohamedhedimagherbi.destinationservice.controller;

import com.mohamedhedimagherbi.destinationservice.entities.Destination;
import com.mohamedhedimagherbi.destinationservice.services.IServiceDestination;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/destination/")
public class DestinationRestController {
    private IServiceDestination IServiceDestination;
    @GetMapping("{id}")
    public Optional<Destination> getById(@PathVariable int id){
        return IServiceDestination.getById(id);
    }
    @PostMapping("add")
    //@PreAuthorize("hasAuthority('ADMIN')")

    public Destination add(@RequestBody Destination destination){
        return IServiceDestination.addDestination(destination);
    }
    @GetMapping("all")
    public List<Destination> allDestinations(){
        return IServiceDestination.getAllDestinations();
    }
    @PutMapping("update/{id}")
    public Destination updateDestination(@PathVariable int id,@RequestBody Destination updatedDestination){
        return IServiceDestination.updateDestination(id,updatedDestination);
    }
    @PostMapping("delete/{id}")
    public void deletePilot(@PathVariable int id){
         IServiceDestination.deleteDestinationById(id);
    }
}
