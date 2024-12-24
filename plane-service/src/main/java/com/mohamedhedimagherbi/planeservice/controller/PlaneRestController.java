package com.mohamedhedimagherbi.planeservice.controller;

import com.mohamedhedimagherbi.planeservice.entities.Plane;
import com.mohamedhedimagherbi.planeservice.services.IServicePlane;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/plane/")
public class PlaneRestController {
    private IServicePlane IServicePlane;
    @GetMapping("{id}")
    public Optional<Plane> getById(@PathVariable int id){
        return IServicePlane.getById(id);
    }
    @PostMapping("add")
    //@PreAuthorize("hasAuthority('ADMIN')")

    public Plane add(@RequestBody Plane plane){
        return IServicePlane.addPlane(plane);
    }
    @GetMapping("all")
    public List<Plane> allPlanes(){
        return IServicePlane.getAllPlanes();
    }
    @PutMapping("update/{id}")
    public Plane updatePlane(@PathVariable int id,@RequestBody Plane updatedPlane){
        return IServicePlane.updatePlane(id,updatedPlane);
    }
    @PostMapping("delete/{id}")
    public void deletePlane(@PathVariable int id){
         IServicePlane.deletePlaneById(id);
    }
}
