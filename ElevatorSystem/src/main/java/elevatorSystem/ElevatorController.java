package elevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private final List<Elevator> elevators;

    public ElevatorController(int numElevators, int capacity) {
        elevators = new ArrayList<>();

        for (int i = 0; i < numElevators; i++) {
            Elevator elevator = new Elevator(i, capacity);
            elevators.add(elevator);
            new Thread(elevator::run).start();
        }
    }

    public void requestElevator(int sourceFloor, int destinationFloor) {
        Elevator optimalElevator=chooseOptimalElevator(sourceFloor,destinationFloor);
        optimalElevator.addRequest(new Request(sourceFloor,destinationFloor));
    }

    private Elevator chooseOptimalElevator(int sourceFloor, int destinationFloor) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(sourceFloor - elevator.getCurrentFloor());

            // Prefer an elevator already moving in the right direction
            if (elevator.isMovingTowards(sourceFloor, destinationFloor) && elevator.getRequestCount() < 3) {
                return elevator; // Immediately return if it's already heading the right way
            }

            // If no moving elevator is found, choose the closest idle one
            if (distance < minDistance && elevator.getRequestCount() < 3) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        return bestElevator; // Assign the best available elevator
    }
}
