package command.problem.step3;

import command.problem.step3.alarm.Alarm;
import command.problem.step3.button.Button;
import command.problem.step3.button.Mode;
import command.problem.step3.lamp.Lamp;

public class Client {

    public static void main(String[] args) {
        Lamp lamp = new Lamp();
        Alarm alarm = new Alarm();
        Button button = new Button(lamp, alarm, Mode.LAMP);

        button.press();

        button.setMode(Mode.ALARM);

        button.press();
    }
}
