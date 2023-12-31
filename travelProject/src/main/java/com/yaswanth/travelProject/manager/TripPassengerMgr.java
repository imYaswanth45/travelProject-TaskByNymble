package com.yaswanth.travelProject.manager;

import com.yaswanth.travelProject.exception.passenger.PassengerAlreadyExistsException;
import com.yaswanth.travelProject.exception.passenger.PassengerNotFoundException;
import com.yaswanth.travelProject.model.Activity;
import com.yaswanth.travelProject.model.Passenger;
import com.yaswanth.travelProject.model.PassengerType;


import java.util.HashMap;
import java.util.Map;

public class TripPassengerMgr {

    Map<String, Passenger> passengerMap = new HashMap<>();

    public void createPassenger(String id, String name, Integer number, PassengerType passengerType, Double budget) {
        if (passengerMap.containsKey(id))
        {
            throw new PassengerAlreadyExistsException();
        }
        passengerMap.put(id, new Passenger(name, number, passengerType, budget));
    }

    public void createPassenger(String id, String name, Integer number, PassengerType passengerType) {
        if (passengerMap.containsKey(id))
        {
            throw new PassengerAlreadyExistsException();
        }
        passengerMap.put(id, new Passenger(name, number, passengerType));
    }

    public Passenger getPassenger(String id) {
        if (!passengerMap.containsKey(id))
        {
            throw new PassengerNotFoundException();
        }
        return passengerMap.get(id);
    }

    public void printPassengerDetails(String id) {
        if (!passengerMap.containsKey(id))
        {
            throw new PassengerNotFoundException();
        }

        Passenger tripPassenger = passengerMap.get(id);

        System.out.println("Passenger Name:" + tripPassenger.getName());
        System.out.println("Passenger Number:" + tripPassenger.getNumber());
        System.out.println("Passenger Balance:" + tripPassenger.getRemainingBudget());

        for (Map.Entry<Activity, Double> entry : tripPassenger.getActivityList().entrySet()) {
            System.out.println("Activity SignedUp :" + entry.getKey().getName());
            System.out.println("Price paid :" + entry.getValue());
            System.out.println("Destination :" + entry.getKey().getDestinationName());
        }
    }
}
