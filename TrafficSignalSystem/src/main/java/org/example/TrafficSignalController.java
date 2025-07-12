package org.example;

import java.util.HashMap;
import java.util.Map;

public class TrafficSignalController {
    private static TrafficSignalController instance;
    private final Map<Integer, Road> roads;

    private TrafficSignalController() {
        roads=new HashMap<>();
    }
    public static synchronized TrafficSignalController getInstance(){
        if(instance == null){
            instance=new TrafficSignalController();
        }
        return instance;
    }

    public void addRoad(Road road) {
        roads.put(road.getId(), road);
    }
    public void removeRoad(String roadId) {
        roads.remove(roadId);
    }

    public void startTrafficControl() {
        new Thread(() -> {
            while (true) {
                for (Road road : roads.values()) {
                    synchronized (this) {  // Synchronization ensures only one road changes state at a time
                        TrafficLight trafficLight = road.getTrafficLight();

                        // Ensure all lights are RED before turning one GREEN
                        roads.values().forEach(r -> r.getTrafficLight().changeSignal(Signal.RED));

                        // Set current road to GREEN
                        trafficLight.changeSignal(Signal.GREEN);
                        System.out.println("Road " + road.getId() + " GREEN");
                        try {
                            Thread.sleep(trafficLight.getGreenDuration());  // Wait for green duration
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Set current road to YELLOW
                        trafficLight.changeSignal(Signal.YELLOW);
                        System.out.println("Road " + road.getId() + " YELLOW");
                        try {
                            Thread.sleep(trafficLight.getYellowDuration());  // Wait for yellow duration
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Set current road to RED
                        trafficLight.changeSignal(Signal.RED);
                        System.out.println("Road " + road.getId() + " RED");
                        try {
                            Thread.sleep(trafficLight.getRedDuration());  // Wait for yellow duration
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Wait a bit before switching to the next road
                        try {
                            Thread.sleep(1000); // Small buffer to ensure clarity in state transitions
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public void handleEmergency(String roadId) {
        Road road = roads.get(roadId);
        if (road != null) {
            TrafficLight trafficLight = road.getTrafficLight();
            trafficLight.changeSignal(Signal.GREEN);
            // Perform emergency handling logic
            // ...
        }
    }



    }
