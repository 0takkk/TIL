package observer.solution.battery;

public class LowBatteryWarning implements Observer {

    private static final int LOW_BATTERY = 40;

    private Battery battery;

    public LowBatteryWarning(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void update() {
        int level = battery.getLevel();

        if(level < LOW_BATTERY) {
            System.out.println("<WARING> Low Battery : " + level + " Compared with " + LOW_BATTERY);
        }
    }
}
