package com.mohamedhedimagherbi.flightservice.controller;

import com.mohamedhedimagherbi.flightservice.entities.Flight;
import com.mohamedhedimagherbi.flightservice.services.IServiceFlight;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/passenger/")
public class FlightRestController {
    private IServiceFlight IServiceFlight;
    @GetMapping("{id}")
    public Optional<Flight> getById(@PathVariable int id){
        return IServiceFlight.getById(id);
    }
    @PostMapping("add")
    //@PreAuthorize("hasAuthority('ADMIN')")

    public Flight add(@RequestBody Flight flight){
        return IServiceFlight.addFlight(flight);
    }
    @GetMapping("all")
    public List<Flight> allFlights(){
        return IServiceFlight.getAllFlights();
    }
    @PutMapping("update/{id}")
    public Flight updateFlight(@PathVariable int id,@RequestBody Flight updatedFlight){
        return IServiceFlight.updateFlight(id,updatedFlight);
    }
    @PostMapping("delete/{id}")
    public void deleteFlight(@PathVariable int id){
         IServiceFlight.deleteFlightById(id);
    }
}
