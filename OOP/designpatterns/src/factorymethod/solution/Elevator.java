package factorymethod.solution;

public class Elevator {

    private int id;

    private int curFloor;

    public Elevator(int id) {
        this.id = id;
        curFloor = 1;
    }

    public void gotoFloor(int destination) {
        System.out.printf("Elevator [%d] Floor : %d ==> %d", id, curFloor, destination);
        curFloor = destination;
    }
}
