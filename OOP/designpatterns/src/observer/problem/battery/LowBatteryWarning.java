package observer.problem.battery;

public class LowBatteryWarning {

    private static final int LOW_BATTERY = 30;

    private Battery battery;

    public LowBatteryWarning(Battery battery) {
        this.battery = battery;
    }

    public void update() {
        int level = battery.getLevel();

        if(level < LOW_BATTERY) {
            System.out.println("<WARING> Low Battery : " + level + " Compared with " + LOW_BATTERY);
        }
    }
}
