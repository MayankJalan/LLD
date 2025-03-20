package org.example;

public class TrafficSignalDemo {
    public static void main(String[] args) {
        TrafficSignalController trafficController = TrafficSignalController.getInstance();

        Road road1=new Road(1,"North Road");
        Road road2=new Road(2,"South Road");
        Road road3=new Road(3,"West Road");
        Road road4=new Road(4,"East Road");

        TrafficLight trafficLight1 = new TrafficLight("TL1", 6000, 3000, 9000);
        TrafficLight trafficLight2 = new TrafficLight("TL2", 6000, 3000, 9000);
        TrafficLight trafficLight3 = new TrafficLight("TL3", 6000, 3000, 9000);
        TrafficLight trafficLight4 = new TrafficLight("TL4", 6000, 3000, 9000);

        road1.setTrafficLight(trafficLight1);
        road2.setTrafficLight(trafficLight2);
        road3.setTrafficLight(trafficLight3);
        road4.setTrafficLight(trafficLight4);

        trafficController.addRoad(road1);
        trafficController.addRoad(road2);
        trafficController.addRoad(road3);
        trafficController.addRoad(road4);

        trafficController.startTrafficControl();
        trafficController.handleEmergency("R2");


    }

}
