package com.yaswanth.travelProject.manager;

import com.yaswanth.travelProject.exception.activity.ActivityCapacityFullException;
import com.yaswanth.travelProject.exception.passenger.InsufficientAmountException;
import com.yaswanth.travelProject.model.Activity;
import com.yaswanth.travelProject.model.Passenger;
import com.yaswanth.travelProject.strategy.PricingStrategy;

import java.util.Map;

public class TripPassengerActivityMgr {

    StrategyMgr  strategyMgr;
    TripPassengerMgr tripPassengerMgr;
    DestinationManager destinationManager;

    public TripPassengerActivityMgr(StrategyMgr strategyMgr,
                                    TripPassengerMgr tripPassengerMgr,
                                    DestinationManager destinationManager) {
        this.strategyMgr = strategyMgr;
        this.tripPassengerMgr = tripPassengerMgr;
        this.destinationManager = destinationManager;
    }
    public void addActivityForTripPassenger(String passengerId, String activityId) {

        Passenger tripPassenger = tripPassengerMgr.getPassenger(passengerId);
        Activity activity = destinationManager.getActivity(activityId);

        Map<Activity, Double> activityList = tripPassenger.getActivityList();

        if (activity.isFull()) {
            throw new ActivityCapacityFullException();
        }
//        strategyMgr = StrategyMgr.getStrategyManagerInstance();

        PricingStrategy pricingStrategy = strategyMgr.determinePricingStrategy(tripPassenger);
        Double activityCost = pricingStrategy.getActivityPricing(tripPassenger, activity);

        if(Double.compare(activityCost, tripPassenger.getRemainingBudget()) < 0) {
            Double remainingBudget = tripPassenger.getRemainingBudget();
            remainingBudget = remainingBudget - activityCost;

            tripPassenger.setRemainingBudget(remainingBudget);
            activityList.put(activity,activityCost);
            tripPassenger.setActivityList(activityList);
        }
        else {
            throw new InsufficientAmountException();
        }

        return ;
    }
}
