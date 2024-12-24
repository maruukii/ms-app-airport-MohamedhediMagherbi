package com.mohamedhedimagherbi.flightservice.entities;

import com.mohamedhedimagherbi.flightservice.model.Pilot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FlightPilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pilotId;
    private int flightId;
    private String cockpit_role;
    @Transient
    private Pilot pilot;
    @Transient
    private Optional<Flight> flight;
}
