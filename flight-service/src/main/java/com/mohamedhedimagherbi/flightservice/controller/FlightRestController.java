package com.mohamedhedimagherbi.flightservice.controller;

import com.mohamedhedimagherbi.flightservice.clients.PlaneRestClient;
import com.mohamedhedimagherbi.flightservice.entities.Flight;
import com.mohamedhedimagherbi.flightservice.entities.FlightLeg;
import com.mohamedhedimagherbi.flightservice.entities.FlightPilot;
import com.mohamedhedimagherbi.flightservice.model.Destination;
import com.mohamedhedimagherbi.flightservice.model.Pilot;
import com.mohamedhedimagherbi.flightservice.model.Plane;
import com.mohamedhedimagherbi.flightservice.services.IServiceFlight;
import com.mohamedhedimagherbi.flightservice.services.IServiceFlightLeg;
import com.mohamedhedimagherbi.flightservice.services.IServiceFlightPilot;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flight/")
public class FlightRestController {
    private IServiceFlight IServiceFlight;
    private IServiceFlightPilot IServiceFlightPilot;
    private IServiceFlightLeg IServiceFlightLeg;
    private PlaneRestClient planeRestClient;
    @GetMapping("{id}")
    public Optional<Flight> getById(@PathVariable int id){
        return IServiceFlight.getById(id);
    }
    @GetMapping("all")
    public List<Flight> allFlights(){
        return IServiceFlight.getAllFlights();
    }
    @PostMapping("add")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Object> add(@RequestBody Flight flight){
        Flight flight1;
        Optional<Plane> plane = Optional.ofNullable(planeRestClient.getPlaneById(flight.getPlaneId()));
        if (plane.isPresent()){
            flight1 = IServiceFlight.addFlight(flight);
            if(flight1!=null) return new ResponseEntity<>(flight, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Flight cannot be created, check provided information",HttpStatus.CONFLICT);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Flight updateFlight(@PathVariable int id,@RequestBody Flight updatedFlight){
        return IServiceFlight.updateFlight(id,updatedFlight);
    }
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity deleteFlight(@PathVariable int id){
         IServiceFlight.deleteFlightById(id);
        return new ResponseEntity<>("Flight deleted with id :"+id, HttpStatus.OK);

    }
    @PostMapping("/assignPilot/{flightId}/{pilotId}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity assignPilots(@PathVariable int flightId, @PathVariable int pilotId,String cockpit_role) {
    IServiceFlightPilot.assignPilotToFlight(flightId, pilotId,cockpit_role);
   return new ResponseEntity<>("Pilot with id "+pilotId+" assigned to flight with id "+flightId,HttpStatus.OK);

    }
    @GetMapping("{flightId}/pilots")
    public List<Pilot> getFlightPilots(@PathVariable int flightId){
    return IServiceFlightPilot.getAllFlightPilots(flightId);
    }
    @GetMapping("flights/pilots")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public List<FlightPilot> allFlightsPilots(){
        return IServiceFlightPilot.AllFlightsPilots();
    }
    @PostMapping("flightleg/{flightId}/{destinationId}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity assignDestination(@PathVariable int flightId, @PathVariable int destinationId, String stop_date, String back_to_flight_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime stopDate = LocalDateTime.parse(stop_date, formatter);
        LocalDateTime backToFlightDate = LocalDateTime.parse(back_to_flight_date, formatter);

        IServiceFlightLeg.assignDestinationToFlight(flightId, destinationId, stopDate, backToFlightDate);
        return new ResponseEntity<>("Destination with id "+destinationId+" set to flight with id "+flightId,HttpStatus.OK);
    }
    @GetMapping("{flightId}/destinations")
    public List<Destination> getFlightDestinations(@PathVariable int flightId){
        return IServiceFlightLeg.getAllFlightDestinations(flightId);
    }
    @GetMapping("flights/destinations")
    public List<FlightLeg> allFlightsDestinations(){
        return IServiceFlightLeg.AllFlightsDestinations();
    }
}
