package com.mohamedhedimagherbi.flightservice.clients;

import com.mohamedhedimagherbi.flightservice.model.Destination;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="DESTINATION-SERVICE")
public interface DestinationRestClient {
    @GetMapping("/api/destination/{id}")
    Destination getDestinationById(@PathVariable int id);
}
