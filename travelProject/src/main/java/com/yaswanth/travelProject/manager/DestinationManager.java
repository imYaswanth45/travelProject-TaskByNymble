package com.yaswanth.travelProject.manager;

import com.yaswanth.travelProject.exception.activity.ActivityAlreadyExistsException;
import com.yaswanth.travelProject.exception.activity.ActivityAlreadyExistsInDestinationException;
import com.yaswanth.travelProject.exception.activity.ActivityNotFoundException;
import com.yaswanth.travelProject.exception.destination.DestinationAlreadyExistsException;
import com.yaswanth.travelProject.exception.destination.DestinationNotFoundException;
import com.yaswanth.travelProject.model.Activity;
import com.yaswanth.travelProject.model.Destination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationManager {

    Map<String, Activity> activityMap = new HashMap<>();
    Map<String, Destination> destinationMap = new HashMap<>();

    public void createActivity(String id, String name, String description, Integer capacity, Double cost) {
        if (activityMap.containsKey(id))
        {
            throw new ActivityAlreadyExistsException();
        }
        activityMap.put(id, new Activity(name,description,cost,capacity));
    }

    public Activity getActivity(String id) {
        if (!activityMap.containsKey(id))
        {
            throw new ActivityNotFoundException();
        }
        return activityMap.get(id);
    }
    public void createDestination(String id, String name) {
        if (destinationMap.containsKey(id))
        {
            throw new DestinationAlreadyExistsException();
        }
        destinationMap.put(id, new Destination(name));
    }

    public Destination getDestination(String id) {
        if (!destinationMap.containsKey(id))
        {
            throw new DestinationNotFoundException();
        }
        return destinationMap.get(id);
    }

    public void addActivityToDestination(String destinationId, String activityId) {
        if (!destinationMap.containsKey(destinationId))
        {
            throw new DestinationNotFoundException();
        }

        if (!activityMap.containsKey(activityId))
        {
            throw new ActivityNotFoundException();
        }

        Destination destination = destinationMap.get(destinationId);
        Activity activity = activityMap.get(activityId);
        List<Activity> activityList1 = destination.getActivityList();

        if (activityList1.contains(activity)) {
            throw new ActivityAlreadyExistsInDestinationException();
        }

        activityList1.add(activity);
        destination.setActivityList(activityList1);
        activity.setDestinationName(destination.getName());

        activityMap.put(activityId,activity);
        destinationMap.put(destinationId, destination);
    }

    public void printActivityDetails(String activityId) {
        if (!activityMap.containsKey(activityId))
        {
            throw new ActivityNotFoundException();
        }

        Activity activity = activityMap.get(activityId);

        System.out.println("ActivityName: " + activity.getName());
        System.out.println("ActivityDescription: " + activity.getDescription());
        System.out.println("ActivityCost: "+activity.getCost());

        System.out.println("SpacesAvailable: " +(activity.getTotalCapacity() - activity.getCurrentCapacity()));
    }
}
