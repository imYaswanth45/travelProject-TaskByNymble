package com.yaswanth.travelProject;

import com.yaswanth.travelProject.controllers.DestinationController;
import com.yaswanth.travelProject.controllers.PassengerController;
import com.yaswanth.travelProject.controllers.TripMgrController;
import com.yaswanth.travelProject.manager.*;
import com.yaswanth.travelProject.strategy.PassengerTypePricingStrategy;
import com.yaswanth.travelProject.strategy.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeEach
	void setUp() {
		TripManager tripManager = new TripManager();
		TripPassengerMgr tripPassengerMgr = new TripPassengerMgr();
		DestinationManager destinationManager = new DestinationManager();
		StrategyMgr strategyMgr = new StrategyMgr();
		PricingStrategy pricingStrategy = new PassengerTypePricingStrategy();

		TripPassengerActivityMgr tripPassengerActivityMgr = new
				TripPassengerActivityMgr(strategyMgr, tripPassengerMgr, destinationManager);

		DestinationController destinationController = new DestinationController(destinationManager);
		PassengerController passengerController = new PassengerController(tripPassengerMgr,tripPassengerActivityMgr);
		TripMgrController tripMgrController = new TripMgrController(tripManager);
	}

	@Test
	void testTripFlow(){

	}
}
