package templatemethod.problem.motor;

import templatemethod.problem.door.Door;
import templatemethod.problem.door.DoorStatus;

public class LGMotor {

    private Door door;
    private MotorStatus motorStatus;

    public LGMotor(Door door) {
        this.door = door;
        motorStatus = MotorStatus.STOPPED;
    }

    public void move(Direction direction) {
        if(motorStatus == MotorStatus.MOVING) {
            return;
        }

        DoorStatus doorStatus = door.getDoorStatus();
        if(doorStatus == DoorStatus.OPENED) {
            door.close();
        }

        this.moveLGMotor(direction);
        motorStatus = MotorStatus.MOVING;
    }

    private void moveLGMotor(Direction direction) {
        // LG Motor를 구동시킴
    }
}
