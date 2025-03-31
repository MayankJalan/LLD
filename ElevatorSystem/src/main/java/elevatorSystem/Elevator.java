package elevatorSystem;

import java.util.ArrayList;
import java.util.List;


public class Elevator {
    private int id;
    private int currentFloor;
    private int capacity;
    private List<Request> requests;
    private Direction currentDirection;

    public Elevator(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.currentDirection = Direction.UP;
        requests = new ArrayList<>();
    }

    public synchronized void addRequest(Request request) {
        if (requests.size() < capacity) {
            requests.add(request);
            System.out.println("Elevator " + id + " added request: " + request);
            notifyAll();
        }
    }

    public synchronized void processRequests() {
        while (true) {
            while (!requests.isEmpty()) {
                // Prioritize requests in the current direction
                requests.sort((r1, r2) -> Integer.compare(r1.getSourceFloor(), r2.getSourceFloor()));
                Request request=getNextRequest();
                processRequest(request);
            }
            try {
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private Request getNextRequest() {
        while (requests.isEmpty()){
            try {
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return requests.remove(0);
    }

    private void processRequest(Request request) {
        int startFloor=currentFloor;
        int endFloor=request.getDestinationFloor();

        if(startFloor<endFloor){
            currentDirection= Direction.UP;
            for(int i=startFloor;i<=endFloor;i++){
                currentFloor=i;
                System.out.println("Elevator " + id + " reached floor " + currentFloor);
                try {
                    Thread.sleep(1000); // Simulating elevator movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            currentDirection= Direction.DOWN;
            for(int i=startFloor;i>=endFloor;i--){
                currentFloor=i;
                System.out.println("Elevator " + id + " reached floor " + currentFloor);
                try {
                    Thread.sleep(1000); // Simulating elevator movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void run() {
        processRequests();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
    public boolean isMovingTowards(int sourceFloor, int destinationFloor) {
        if (requests.isEmpty()) {
            return true; // Idle elevators can take any request
        }
        if (currentDirection == Direction.UP && sourceFloor >= currentFloor) {
            return true; // Elevator is going up and request is above
        }
        if (currentDirection == Direction.DOWN && sourceFloor <= currentFloor) {
            return true; // Elevator is going down and request is below
        }
        return false; // Otherwise, not optimal
    }


    public int getRequestCount() {
        return requests.size();
    }
}