package state.solution.state;

import state.solution.light.Light;

public interface LightState {

    void on(Light light);

    void off(Light light);
}
