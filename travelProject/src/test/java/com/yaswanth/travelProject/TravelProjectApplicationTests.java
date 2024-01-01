package com.yaswanth.travelProject;

import com.yaswanth.travelProject.controllers.DestinationController;
import com.yaswanth.travelProject.controllers.PassengerController;
import com.yaswanth.travelProject.controllers.TripMgrController;
import com.yaswanth.travelProject.manager.*;
import com.yaswanth.travelProject.model.Destination;
import com.yaswanth.travelProject.model.PassengerType;
import com.yaswanth.travelProject.strategy.PassengerTypePricingStrategy;
import com.yaswanth.travelProject.strategy.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class TravelProjectApplicationTests {

	DestinationController destinationController;
	PassengerController passengerController;
	TripMgrController tripMgrController;

	@BeforeEach
	void setUp() {

		TripPassengerMgr tripPassengerMgr = new TripPassengerMgr();
		DestinationManager destinationManager = new DestinationManager();
		StrategyMgr strategyMgr = new StrategyMgr();
		PricingStrategy pricingStrategy = new PassengerTypePricingStrategy();

		TripPassengerActivityMgr tripPassengerActivityMgr = new
				TripPassengerActivityMgr(strategyMgr, tripPassengerMgr, destinationManager);
		TripManager tripManager = new TripManager(tripPassengerMgr, destinationManager);

		destinationController = new DestinationController(destinationManager);
		passengerController = new PassengerController(tripPassengerMgr,tripPassengerActivityMgr);
		tripMgrController = new TripMgrController(tripManager);
	}

	@Test
	void testTripFlow(){

		String p1 = "p1";
		passengerController.createPassenger(p1,"Passenger1", 1, PassengerType.STANDARD, 100.0);
		String p2 = "p2";
		passengerController.createPassenger(p2,"Passenger2", 2, PassengerType.GOLD, 50.0);
		String p3 = "p3";
		passengerController.createPassenger(p3,"Passenger3", 3, PassengerType.PREMIUM, 150.0);
		String p4 = "p4";
		passengerController.createPassenger(p4,"Passenger4", 4, PassengerType.STANDARD, 200.0);
		String p5 = "p5";
		passengerController.createPassenger(p5,"Passenger5", 5, PassengerType.GOLD, 100.0);
		String p6 = "p6";
		passengerController.createPassenger(p6,"Passenger6", 6, PassengerType.STANDARD, 40.0);
		String p7 = "p7";
		passengerController.createPassenger(p7,"Passenger7", 7, PassengerType.GOLD, 35.0);
		String p8 = "p8";
		passengerController.createPassenger(p8,"Passenger8", 8, PassengerType.PREMIUM, 40.0);


		String d1 = "d1";
		destinationController.createDestination(d1, "Destination1");
		String d2 = "d2";
		destinationController.createDestination(d2, "Destination2");
		String d3 = "d3";
		destinationController.createDestination(d3, "Destination3");
		String d4 = "d4";
		destinationController.createDestination(d4, "Destination4");

		String a1 = "a1";
		destinationController.createActivity(a1, "Activity1", "Performs Activity1", 10, 25.0);
		String a2 = "a2";
		destinationController.createActivity(a2, "Activity2", "Performs Activity2", 5, 35.0);
		String a3 = "a3";
		destinationController.createActivity(a3, "Activity3", "Performs Activity3", 15, 45.0);
		String a4 = "a4";
		destinationController.createActivity(a4, "Activity4", "Performs Activity4", 3, 50.0);
		String a5 = "a5";
		destinationController.createActivity(a5, "Activity5", "Performs Activity5", 1, 100.0);
		String a6 = "a6";
		destinationController.createActivity(a6, "Activity6", "Performs Activity6", 2, 50.0);
		String a7 = "a7";
		destinationController.createActivity(a7, "Activity7", "Performs Activity7", 4, 25.0);
		String a8 = "a8";
		destinationController.createActivity(a8, "Activity8", "Performs Activity8", 5, 55.0);


		destinationController.addActivityToDestination(d1,a1);
		destinationController.addActivityToDestination(d1,a2);
		destinationController.addActivityToDestination(d1,a3);

		destinationController.addActivityToDestination(d2,a4);
		destinationController.addActivityToDestination(d2,a5);

		destinationController.addActivityToDestination(d3,a6);
		destinationController.addActivityToDestination(d3,a7);

		destinationController.addActivityToDestination(d4,a8);


		String t1 = "t1";
		tripMgrController.initiateTrip(t1, "Trip1", 4);
		tripMgrController.updateTrip(t1, d1);
		tripMgrController.updateTrip(t1,d3);
		tripMgrController.updateTrip(t1,d4);


		passengerController.addActivity(p1,a1);
		passengerController.addActivity(p1,a2);
		passengerController.addActivity(p2,a3);
		passengerController.addActivity(p3,a4);
		passengerController.addActivity(p3,a5);


		tripMgrController.addPassenger(t1,p1);
		tripMgrController.addPassenger(t1,p2);
		tripMgrController.addPassenger(t1,p3);
		tripMgrController.addPassenger(t1,p6);

		passengerController.printPassengerDetails(p1);
		System.out.println("_______________________");
		passengerController.printPassengerDetails(p2);
		System.out.println("_______________________");
		passengerController.printPassengerDetails(p3);
		System.out.println("_______________________");
		passengerController.printPassengerDetails(p6);
		System.out.println("_______________________");


		destinationController.printActivity(a1);
		System.out.println("_______________________");
		destinationController.printActivity(a2);
		System.out.println("_______________________");
		destinationController.printActivity(a3);
		System.out.println("_______________________");
		destinationController.printActivity(a4);
		System.out.println("_______________________");
		destinationController.printActivity(a5);
		System.out.println("_______________________");


		tripMgrController.getTripItinerary(t1);
		System.out.println("_______________________");
		tripMgrController.getPassengerList(t1);

	}
}
