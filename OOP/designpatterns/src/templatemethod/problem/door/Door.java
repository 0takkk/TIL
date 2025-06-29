package templatemethod.problem.door;

public class Door {

    private DoorStatus doorStatus;

    public Door() {
        this.doorStatus = DoorStatus.CLOSE;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void open() {
        doorStatus = DoorStatus.OPENED;
    }

    public void close() {
        doorStatus = DoorStatus.CLOSE;
    }
}
