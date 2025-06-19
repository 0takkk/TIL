package command.solution.button;

import command.solution.command.Command;

public class Button {

    private Command command;

    public Button(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }
}
