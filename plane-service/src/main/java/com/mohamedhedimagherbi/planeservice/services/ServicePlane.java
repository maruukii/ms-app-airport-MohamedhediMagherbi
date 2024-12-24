package com.mohamedhedimagherbi.planeservice.services;

import com.mohamedhedimagherbi.planeservice.entities.Plane;
import com.mohamedhedimagherbi.planeservice.repository.PlaneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicePlane implements IServicePlane {
    private PlaneRepository planeRepository;
    public Optional<Plane> getById(int id){
        return planeRepository.findById(id);
    }
    public Plane addPlane(Plane plane){
        plane.setCreated_At(LocalDateTime.now());
        plane.setModified_At(LocalDateTime.now());
       return planeRepository.save(plane);
    }
    public List<Plane> getAllPlanes(){
        return planeRepository.findAll();
    }

    @Override
    public void deletePlaneById(int id) {
        Optional<Plane> existingPlane = planeRepository.findById(id);
        if (existingPlane.isPresent())
                planeRepository.deleteById(id);
        else
            throw new RuntimeException("Plane not found with id: " + id);
    }


    public Plane updatePlane(int id,Plane updatedPlane) {
        Optional<Plane> existingPlane = planeRepository.findById(id);

        if (existingPlane.isPresent()) {
            Plane plane = existingPlane.get();
            if (updatedPlane.getModel_name()!=null)
            plane.setModel_name(updatedPlane.getModel_name());
            if(updatedPlane.getModel_type()!=null)
            plane.setModel_type(updatedPlane.getModel_type());
            plane.setModified_At(LocalDateTime.now());
            if(updatedPlane.getMax_capacity()!=0)
            plane.setMax_capacity(updatedPlane.getMax_capacity());

            return planeRepository.save(plane);
        } else {
            throw new RuntimeException("Plane not found with id: " + id);
        }
    }

}
