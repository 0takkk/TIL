package templatemethod.solution;

import templatemethod.solution.door.Door;
import templatemethod.solution.motor.Direction;
import templatemethod.solution.motor.HyundaiMotor;
import templatemethod.solution.motor.LGMotor;

public class Client {
    public static void main(String[] args) {
        Door door = new Door();
        HyundaiMotor hyundaiMotor = new HyundaiMotor(door);
        hyundaiMotor.move(Direction.UP);

        LGMotor lgMotor = new LGMotor(door);
        lgMotor.move(Direction.DOWN);
    }
}
