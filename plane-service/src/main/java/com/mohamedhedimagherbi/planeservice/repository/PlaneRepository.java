package com.mohamedhedimagherbi.planeservice.repository;

import com.mohamedhedimagherbi.planeservice.entities.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane,Integer> {

}