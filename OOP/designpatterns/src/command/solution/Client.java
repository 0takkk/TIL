package command.solution;

import command.solution.alarm.Alarm;
import command.solution.button.Button;
import command.solution.command.AlarmStartCommand;
import command.solution.command.Command;
import command.solution.command.LampOnCommand;
import command.solution.lamp.Lamp;

public class Client {

    public static void main(String[] args) {
        Lamp lamp = new Lamp();
        Command lampOnCommand = new LampOnCommand(lamp);

        Button button1 = new Button(lampOnCommand);
        button1.press();

        Alarm alarm = new Alarm();
        Command alarmStartCommand = new AlarmStartCommand(alarm);

        Button button2 = new Button(alarmStartCommand);
        button2.press();

        button2.setCommand(lampOnCommand);  // 램프 켜는 커맨드로 변경
        button2.press();
    }
}
