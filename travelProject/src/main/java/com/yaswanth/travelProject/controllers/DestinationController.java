package com.yaswanth.travelProject.controllers;

import com.yaswanth.travelProject.manager.DestinationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {

    DestinationManager destinationManager;

    public DestinationController(DestinationManager destinationManager) {
        this.destinationManager = destinationManager;
    }

    @RequestMapping(value = "/destination", method = RequestMethod.POST)
    public ResponseEntity createDestination(String id, String name) {
        destinationManager.createDestination(id, name);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/destination/activity", method = RequestMethod.POST)
    public ResponseEntity createActivity(String id, String name, String description, Integer capacity, Double cost) {
        destinationManager.createActivity(id,name,description,capacity,cost);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/destination/activity", method = RequestMethod.PUT)
    public ResponseEntity addActivityToDestination(String destinationId, String activityId) {
        destinationManager.addActivityToDestination(destinationId, activityId);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/destination/activity", method = RequestMethod.GET)
    public ResponseEntity printActivity(String activityId) {
        destinationManager.printActivityDetails(activityId);
        return ResponseEntity.ok("");
    }

}
