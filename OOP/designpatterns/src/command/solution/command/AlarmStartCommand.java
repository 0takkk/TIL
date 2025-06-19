package command.solution.command;

import command.solution.alarm.Alarm;

public class AlarmStartCommand implements Command {

    private Alarm alarm;

    public AlarmStartCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.start();
    }
}
