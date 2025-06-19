package command.problem.step2;

import command.problem.step2.alarm.Alarm;
import command.problem.step2.button.Button;

public class Client {

    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        Button button = new Button(alarm);

        button.press();
    }
}
