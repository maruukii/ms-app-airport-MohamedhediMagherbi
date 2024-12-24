package com.mohamedhedimagherbi.flightservice.clients;

import com.mohamedhedimagherbi.flightservice.model.Pilot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="PILOT-SERVICE")
public interface PilotRestClient {
    @GetMapping("/api/pilot/{id}")
    Pilot getPilotById(@PathVariable int id);
}
