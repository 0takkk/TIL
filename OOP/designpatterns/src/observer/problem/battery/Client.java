package observer.problem.battery;

public class Client {

    public static void main(String[] args) {
        Battery battery = new Battery();

        BatteryLevelDisplay batteryLevelDisplay = new BatteryLevelDisplay(battery);
        LowBatteryWarning lowBatteryWarning = new LowBatteryWarning(battery);

        battery.setDisplay(batteryLevelDisplay);
        battery.setWaring(lowBatteryWarning);

        battery.consume(20);
        battery.consume(50);
        battery.consume(10);
    }
}
