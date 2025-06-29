package templatemethod.solution.motor;

import templatemethod.solution.door.Door;
import templatemethod.solution.door.DoorStatus;

public abstract class Motor {

    private Door door;
    private MotorStatus motorStatus;

    public Motor(Door door) {
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

        moveMotor(direction);

        motorStatus = MotorStatus.MOVING;
    }

    protected abstract void moveMotor(Direction direction);
}
