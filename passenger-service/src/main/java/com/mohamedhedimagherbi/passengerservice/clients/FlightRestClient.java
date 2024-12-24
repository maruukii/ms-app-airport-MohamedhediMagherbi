package com.mohamedhedimagherbi.passengerservice.clients;
import com.mohamedhedimagherbi.passengerservice.model.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="FLIGHT-SERVICE")
public interface FlightRestClient {
    @GetMapping("/api/flight/{id}")
    Flight getFlightById(@PathVariable int id);
}