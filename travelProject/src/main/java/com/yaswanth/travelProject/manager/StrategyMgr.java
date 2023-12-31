package com.yaswanth.travelProject.manager;

import com.yaswanth.travelProject.model.Passenger;
import com.yaswanth.travelProject.strategy.PassengerTypePricingStrategy;
import com.yaswanth.travelProject.strategy.PricingStrategy;


public class StrategyMgr {
    public PricingStrategy determinePricingStrategy(Passenger tripPassenger) {
        return new PassengerTypePricingStrategy();
    }
}
