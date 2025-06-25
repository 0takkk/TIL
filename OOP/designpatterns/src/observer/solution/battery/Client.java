package observer.solution.battery;

public class Client {

    public static void main(String[] args) {
        Battery battery = new Battery();

        Observer batteryLevelDisplay = new BatteryLevelDisplay(battery);
        Observer lowBatteryWarning = new LowBatteryWarning(battery);

        battery.attach(batteryLevelDisplay);
        battery.attach(lowBatteryWarning);

        battery.consume(50);
        battery.consume(20);
        battery.consume(10);
    }
}
