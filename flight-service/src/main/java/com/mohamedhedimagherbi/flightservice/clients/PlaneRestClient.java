package com.mohamedhedimagherbi.flightservice.clients;

import com.mohamedhedimagherbi.flightservice.model.Plane;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PLANE-SERVICE")
public interface PlaneRestClient {
    @GetMapping("/api/plane/{id}")
    Plane getPlaneById(@PathVariable int id);
}