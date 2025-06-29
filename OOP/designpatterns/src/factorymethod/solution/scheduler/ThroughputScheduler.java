package factorymethod.solution.scheduler;

import factorymethod.solution.Direction;
import factorymethod.solution.ElevatorManager;

public class ThroughputScheduler implements ElevatorScheduler {

    @Override
    public int selectElevator(ElevatorManager elevatorManager, int destination, Direction direction) {
        return 0;  // 임의 선택
    }
}
