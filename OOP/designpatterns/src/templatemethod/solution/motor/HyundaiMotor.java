package templatemethod.solution.motor;

import templatemethod.solution.door.Door;

public class HyundaiMotor extends Motor {

    public HyundaiMotor(Door door) {
        super(door);
    }

    @Override
    protected void moveMotor(Direction direction) {
        System.out.println("Hyundai Motor를 구동시킴");
    }
}
