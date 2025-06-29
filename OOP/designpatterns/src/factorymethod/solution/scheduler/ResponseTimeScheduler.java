package factorymethod.solution.scheduler;

import factorymethod.solution.Direction;
import factorymethod.solution.ElevatorManager;

public class ResponseTimeScheduler implements ElevatorScheduler {

    @Override
    public int selectElevator(ElevatorManager elevatorManager, int destination, Direction direction) {
        return 1;  // 임의 선택
    }
}
