package com.yaswanth.travelProject.manager;

import com.yaswanth.travelProject.exception.destination.DestinationAlreadyExistsInTripException;
import com.yaswanth.travelProject.exception.passenger.PassengerAlreadyExistsInTripException;
import com.yaswanth.travelProject.exception.trip.TripAlreadyExistException;
import com.yaswanth.travelProject.exception.trip.TripCapacityFullException;
import com.yaswanth.travelProject.exception.trip.TripNotFoundException;
import com.yaswanth.travelProject.model.Activity;
import com.yaswanth.travelProject.model.Destination;
import com.yaswanth.travelProject.model.Passenger;
import com.yaswanth.travelProject.model.Trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripManager {

    Map<String, Trip> tripMap = new HashMap<>();

    DestinationManager destinationManager;
    TripPassengerMgr passengerMgr;

    public TripManager(TripPassengerMgr passengerMgr, DestinationManager destinationManager) {
        this.destinationManager = destinationManager;
        this.passengerMgr = passengerMgr;
    }

    public void createTrip(String id, String name, Integer capacity, List<Destination> destinationList) {
        if (tripMap.containsKey(id))
        {
            throw new TripAlreadyExistException();
        }
        Trip trip = new Trip(name, capacity, destinationList);
        tripMap.put(id, trip);
    }

    public void createTrip(String id, String name, Integer capacity) {
        if (tripMap.containsKey(id))
        {
            throw new TripAlreadyExistException();
        }
        tripMap.put(id, new Trip(name, capacity));
    }

    public void addDestinationToTrip(String tripId, String destinationId) {
        if (!tripMap.containsKey(tripId)) {
            throw new TripNotFoundException();
        }

        Trip trip = tripMap.get(tripId);
        Destination destination = destinationManager.getDestination(destinationId);

        List<Destination> destinationList = trip.getDestinationList();

        if (destinationList.contains(destination)) {
            throw new DestinationAlreadyExistsInTripException();
        }
        destinationList.add(destination);
        trip.setDestinationList(destinationList);

        tripMap.put(tripId, trip);
    }

    public Boolean addPassengerToTrip(String tripId, String passengerId) {

        if (!tripMap.containsKey(tripId))
        {
            throw new TripNotFoundException();
        }

        Trip trip = tripMap.get(tripId);
        if (trip.isFull())
        {
            throw new TripCapacityFullException();
        }
        Passenger passenger = passengerMgr.getPassenger(passengerId);

        List<Passenger> passengerList = trip.getPassengerList();
        if (passengerList.contains(passenger)) {
            throw new PassengerAlreadyExistsInTripException();
        }
        passengerList.add(passenger);

        trip.setCurrentCapacity(trip.getCurrentCapacity()+1);
        trip.setPassengerList(passengerList);

        tripMap.put(tripId, trip);
        return Boolean.TRUE;
    }

    public void printPassengerList(String id) {
        Trip trip = tripMap.get(id);

        if (!tripMap.containsKey(id)) {
            throw new TripNotFoundException();
        }

        System.out.println("Package Name :" + trip.getName());
        System.out.println("Package Capacity :" + trip.getTotalCapacity());
        System.out.println("Passengers Enrolled:" + trip.getCurrentCapacity());

        for (Passenger passenger : trip.getPassengerList()) {
            System.out.println("Passenger Name :" + passenger.getName());
            System.out.println("Passenger Number :" + passenger.getNumber());
        }
    }

    public void printItinerary(String id) {
        Trip trip = tripMap.get(id);

        if (!tripMap.containsKey(id)) {
            throw new TripNotFoundException();
        }

        System.out.println("Package Name :" + trip.getName());

        for (Destination destination : trip.getDestinationList()) {
            System.out.println("Destination :" + destination.getName());

            for (Activity activity : destination.getActivityList()) {
                System.out.println("Activity :" + activity.getName());
                System.out.println("Activity cost:" + activity.getCost());
                System.out.println("Activity Capacity:" + activity.getTotalCapacity());
                System.out.println("Activity Description:" + activity.getDescription());
            }
        }
    }
}
