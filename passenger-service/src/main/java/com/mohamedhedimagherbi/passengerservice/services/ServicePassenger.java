package com.mohamedhedimagherbi.passengerservice.services;

import com.mohamedhedimagherbi.passengerservice.clients.FlightRestClient;
import com.mohamedhedimagherbi.passengerservice.entities.Passenger;
import com.mohamedhedimagherbi.passengerservice.model.Flight;
import com.mohamedhedimagherbi.passengerservice.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicePassenger implements IServicePassenger {
    private PassengerRepository passengerRepository;
    private FlightRestClient flightRestClient;
    public Optional<Passenger> getById(int id){
        return passengerRepository.findById(id);
    }
    public Passenger addPassenger(Passenger passenger){
         Flight flight = flightRestClient.getFlightById(passenger.getFlightId());
        if (flight==null) throw new RuntimeException("No flight found with id: "+passenger.getFlightId());
        passenger.setFlight(flight);
        passenger.setCreated_At(LocalDateTime.now());
        passenger.setModified_At(LocalDateTime.now());
       return passengerRepository.save(passenger);
    }
    public List<Passenger> getAllPassengers(){
        List <Passenger> passengers=passengerRepository.findAll();
        for (Passenger passenger: passengers
             ) {
            passenger.setFlight(flightRestClient.getFlightById(passenger.getFlightId()));
        }
        return passengers;
    }

    @Override
    public void deletePassengerById(int id) {
        Optional<Passenger> existingPassenger = passengerRepository.findById(id);
        if (existingPassenger.isPresent())
            passengerRepository.deleteById(id);
        else
            throw new RuntimeException("Passenger not found with id: " + id);
    }


    public Passenger updatePassenger(int id,Passenger updatedPassenger) {
        Optional<Passenger> existingPassenger = passengerRepository.findById(id);

        if (existingPassenger.isPresent()) {
            Passenger passenger = existingPassenger.get();
            if(updatedPassenger.getFirst_name()!=null)
            passenger.setFirst_name(updatedPassenger.getFirst_name());
            if(updatedPassenger.getLast_name()!=null)
            passenger.setLast_name(updatedPassenger.getLast_name());
            if(updatedPassenger.getPassport_code()!=null)
            passenger.setPassport_code(updatedPassenger.getPassport_code());
            if(updatedPassenger.getDate_birth()!=null)
            passenger.setDate_birth(updatedPassenger.getDate_birth());
            if(updatedPassenger.getTicket_type()!=null)
            passenger.setTicket_type(updatedPassenger.getTicket_type());
            if(updatedPassenger.getPhone_number()!=0)
            passenger.setPhone_number(updatedPassenger.getPhone_number());
            passenger.setModified_At(LocalDateTime.now());
            if(updatedPassenger.getFlight()!=null)
            passenger.setFlight(updatedPassenger.getFlight());
            if(updatedPassenger.getFlightId()!=0)
            passenger.setFlightId(updatedPassenger.getFlightId());
            return passengerRepository.save(passenger);
        } else {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
    }


    public List<Passenger> PassengersByFlight(int id) {
        return passengerRepository.findAllByFlightId(id);
    }

}
