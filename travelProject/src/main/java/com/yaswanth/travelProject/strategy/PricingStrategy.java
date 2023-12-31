package com.yaswanth.travelProject.strategy;

import com.yaswanth.travelProject.model.Activity;
import com.yaswanth.travelProject.model.Passenger;


public interface PricingStrategy {

    Double getActivityPricing(Passenger tripPassenger, Activity activity);
}
