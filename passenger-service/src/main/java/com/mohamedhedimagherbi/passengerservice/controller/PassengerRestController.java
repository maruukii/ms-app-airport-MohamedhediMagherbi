package com.mohamedhedimagherbi.passengerservice.controller;

import com.mohamedhedimagherbi.passengerservice.entities.Passenger;
import com.mohamedhedimagherbi.passengerservice.services.IServicePassenger;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/passenger/")
public class PassengerRestController {
    private IServicePassenger IServicePassenger;
    @GetMapping("{id}")
    public Optional<Passenger> getById(@PathVariable int id){
        return IServicePassenger.getById(id);
    }
    @PostMapping("add")
    //@PreAuthorize("hasAuthority('ADMIN')")

    public Passenger add(@RequestBody Passenger passenger){
        return IServicePassenger.addPassenger(passenger);
    }
    @GetMapping("all")
    public List<Passenger> allPassengers(){
        return IServicePassenger.getAllPassengers();
    }
    @PutMapping("update/{id}")
    public Passenger updatePassenger(@PathVariable int id,@RequestBody Passenger updatedPassenger){
        return IServicePassenger.updatePassenger(id,updatedPassenger);
    }
    @PostMapping("delete/{id}")
    public void deletePassenger(@PathVariable int id){
         IServicePassenger.deletePassengerById(id);
    }
    @GetMapping("all/{id}")
    public List<Passenger> PassengersByFlight(@PathVariable int id){
        return IServicePassenger.PassengersByFlight(id);
    }
}