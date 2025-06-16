package state.solution.light;

import state.solution.state.OFF;
import state.solution.state.State;

public class Light {

    private State state;

    public Light() {
        state = OFF.getInstance();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void on() {
        state.on(this);
    }

    public void off() {
        state.off(this);
    }
}
