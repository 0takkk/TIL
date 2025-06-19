package command.solution.command;

import command.solution.lamp.Lamp;

public class LampOffCommand implements Command {

    private Lamp lamp;

    public LampOffCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void execute() {
        lamp.turnOff();
    }
}
