package templatemethod.problem;

import templatemethod.problem.door.Door;
import templatemethod.problem.motor.Direction;
import templatemethod.problem.motor.HyundaiMotor;

public class Client {
    public static void main(String[] args) {
        Door door = new Door();
        HyundaiMotor hyundaiMotor = new HyundaiMotor(door);

        hyundaiMotor.move(Direction.UP);
    }
}
