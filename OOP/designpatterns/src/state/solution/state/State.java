package state.solution.state;

import state.solution.light.Light;

public interface State {

    void on(Light light);

    void off(Light light);
}
