package composite.solution.computer;

import java.util.ArrayList;
import java.util.List;

public class Computer extends ComputerDevice {

    private List<ComputerDevice> computerDevices = new ArrayList<>();

    public void addDevice(ComputerDevice computerDevice) {
        computerDevices.add(computerDevice);
    }

    @Override
    public int getPrice() {
        return computerDevices.stream()
                .mapToInt(ComputerDevice::getPrice)
                .sum();
    }

    @Override
    public int getPower() {
        return computerDevices.stream()
                .mapToInt(ComputerDevice::getPower)
                .sum();
    }
}
