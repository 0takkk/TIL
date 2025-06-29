package factorymethod.solution;

import factorymethod.solution.scheduler.ElevatorScheduler;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {

    private List<Elevator> elevators;

    private SchedulingStrategyID strategyID;

    public ElevatorManager(int elevatorCount, SchedulingStrategyID strategyID) {
        elevators = new ArrayList<>(elevatorCount);
        for(int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i));
        }

        this.strategyID = strategyID;
    }

    public void requestElevator(int destination, Direction direction) {
        ElevatorScheduler scheduler = SchedulerFactory.getScheduler(strategyID);
        int selectedElevator = scheduler.selectElevator(this, destination, direction);
        elevators.get(selectedElevator).gotoFloor(destination);
    }
}
