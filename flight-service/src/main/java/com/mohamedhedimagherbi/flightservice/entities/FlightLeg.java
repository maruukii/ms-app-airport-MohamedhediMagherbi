package com.mohamedhedimagherbi.flightservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mohamedhedimagherbi.flightservice.model.Destination;
import com.mohamedhedimagherbi.flightservice.model.Pilot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FlightLeg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int destinationId;
    private int flightId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime stop_date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime back_to_flight_date;
    @Transient
    private Destination destination;
    @Transient
    private Optional<Flight> flight;
}
