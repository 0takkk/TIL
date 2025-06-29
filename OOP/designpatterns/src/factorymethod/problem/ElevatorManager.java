package factorymethod.problem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {

    private List<Elevator> elevators;

    private ThroughputScheduler scheduler;

    public ElevatorManager(int elevatorCount) {
        elevators = new ArrayList<>(elevatorCount);
        for(int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i));
        }

        scheduler = new ThroughputScheduler();
    }

    public void requestElevator(int destination, Direction direction) {
        int selectedElevator = scheduler.selectElevator(this, destination, direction);

        elevators.get(selectedElevator).gotoFloor(destination);
    }
}
