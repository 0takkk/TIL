package state.solution.state;

import state.solution.light.Light;

public class SLEEPING implements LightState {

    private static SLEEPING sleeping = new SLEEPING();

    private SLEEPING() {}

    public static SLEEPING getInstance() {
        return sleeping;
    }

    @Override
    public void on(Light light) {
        System.out.println("Light On Back!");
        light.setState(ON.getInstance());
    }

    @Override
    public void off(Light light) {
        System.out.println("Light Off Back!");
        light.setState(OFF.getInstance());
    }
}
