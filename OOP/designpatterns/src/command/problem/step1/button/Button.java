package command.problem.step1.button;

import command.problem.step1.lamp.Lamp;

public class Button {

    private Lamp lamp;

    public Button(Lamp lamp) {
        this.lamp = lamp;
    }

    public void press() {
        lamp.turnOn();
    }
}
