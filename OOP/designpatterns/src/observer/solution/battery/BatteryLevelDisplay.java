package observer.solution.battery;

public class BatteryLevelDisplay implements Observer {

    private Battery battery;

    public BatteryLevelDisplay(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void update() {
        int level = battery.getLevel();
        System.out.println("Level : " + level);
    }
}
