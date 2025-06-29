package factorymethod.solution.scheduler;

import factorymethod.solution.Direction;
import factorymethod.solution.ElevatorManager;

public interface ElevatorScheduler {

    int selectElevator(ElevatorManager elevatorManager, int destination, Direction direction);
}
