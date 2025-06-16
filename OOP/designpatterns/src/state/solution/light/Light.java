package state.solution.light;

import state.solution.state.OFF;
import state.solution.state.LightState;

public class Light {

    private LightState lightState;

    public Light() {
        lightState = OFF.getInstance();
    }

    public void setState(LightState lightState) {
        this.lightState = lightState;
    }

    public void on() {
        lightState.on(this);
    }

    public void off() {
        lightState.off(this);
    }
}
