package com.yaswanth.travelProject.controllers;

import com.yaswanth.travelProject.manager.TripManager;
import com.yaswanth.travelProject.model.Destination;
import com.yaswanth.travelProject.model.Passenger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TripMgrController {

    TripManager tripManager;

    public TripMgrController(TripManager tripManager) {
        this.tripManager = tripManager;
    }

    @RequestMapping(value = "/trip", method = RequestMethod.POST)
    public ResponseEntity createTrip(String id, String tripName, Integer capacity, List<Destination> destinationList) {
        tripManager.createTrip(id, tripName, capacity, destinationList);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/trip/init", method = RequestMethod.POST)
    public ResponseEntity initiateTrip(String id, String tripName, Integer capacity) {
        tripManager.createTrip(id, tripName, capacity);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/trip", method = RequestMethod.PUT)
    public ResponseEntity updateTrip(String id, Destination destination) {
        tripManager.addDestinationToTrip(id, destination);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/trip/passenger", method = RequestMethod.PUT)
    public ResponseEntity addPassenger(String id, Passenger passenger) {
        tripManager.addPassengerToTrip(id, passenger);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/trip/passenger", method = RequestMethod.GET)
    public ResponseEntity getPassengerList(String id) {
        tripManager.printPassengerList(id);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/trip/itinerary", method = RequestMethod.GET)
    public ResponseEntity getTripItinerary(String id) {
        tripManager.printItinerary(id);
        return ResponseEntity.ok("");
    }

}
