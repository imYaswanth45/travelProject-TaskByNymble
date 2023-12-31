package com.yaswanth.travelProject.controllers;

import com.yaswanth.travelProject.manager.TripPassengerActivityMgr;
import com.yaswanth.travelProject.manager.TripPassengerMgr;
import com.yaswanth.travelProject.model.PassengerType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {

    TripPassengerMgr passengerMgr;

    TripPassengerActivityMgr tripPassengerActivityMgr;

    public PassengerController(TripPassengerMgr passengerMgr, TripPassengerActivityMgr tripPassengerActivityMgr) {
        this.passengerMgr = passengerMgr;
        this.tripPassengerActivityMgr = tripPassengerActivityMgr;
    }

    @RequestMapping(value = "/passenger", method = RequestMethod.POST)
    public ResponseEntity createPassenger(String id,
                                          String name,
                                          Integer number,
                                          PassengerType passengerType,
                                          Double budget) {
        passengerMgr.createPassenger(id, name, number, passengerType, budget);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/passenger", method = RequestMethod.GET)
    public ResponseEntity printPassengerDetails(String id) {
        passengerMgr.printPassengerDetails(id);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/passenger/activity", method = RequestMethod.PUT)
    public ResponseEntity addActivity(String passengerId, String activityId) {
        tripPassengerActivityMgr.addActivityForTripPassenger(passengerId, activityId);
        return ResponseEntity.ok("");
    }
}
