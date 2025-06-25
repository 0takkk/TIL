package observer.problem.battery;

public class Battery {

    private int level = 100;

    private BatteryLevelDisplay display;

    private LowBatteryWarning waring;

    public void setDisplay(BatteryLevelDisplay display) {
        this.display = display;
    }

    public void setWaring(LowBatteryWarning waring) {
        this.waring = waring;
    }

    public void consume(int amount) {
        level -= amount;
        display.update();
        waring.update();
    }

    public int getLevel() {
        return level;
    }
}
