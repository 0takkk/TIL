package templatemethod.problem.motor;

import templatemethod.problem.door.Door;
import templatemethod.problem.door.DoorStatus;

public class HyundaiMotor {

    private Door door;
    private MotorStatus motorStatus;

    public HyundaiMotor(Door door) {
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

        this.moveHyundaiMotor(direction);
        motorStatus = MotorStatus.MOVING;
    }

    private void moveHyundaiMotor(Direction direction) {
        // Hyundai Motor를 구동시킴
    }
}
