package org.example;

public class Road {
    private int id;
    private String name;
    private TrafficLight trafficLight;

    public Road(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }


    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }
}
