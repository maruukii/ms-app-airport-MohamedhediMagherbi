package com.mohamedhedimagherbi.planeservice.services;

import com.mohamedhedimagherbi.planeservice.entities.Plane;

import java.util.List;
import java.util.Optional;

public interface IServicePlane {
    public Optional<Plane> getById(int id);
    public Plane addPlane(Plane plane);
    public List<Plane> getAllPlanes();
    public void deletePlaneById(int id);
    public Plane updatePlane(int id,Plane updatedPlane);
}
