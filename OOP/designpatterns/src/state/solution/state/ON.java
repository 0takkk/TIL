package state.solution.state;

import state.solution.light.Light;

public class ON implements LightState {

    private static ON on = new ON();

    private ON() {}

    public static ON getInstance() {
        return on;
    }

    @Override
    public void on(Light light) {
        light.setState(SLEEPING.getInstance());
        System.out.println("취침등 On!");
    }

    @Override
    public void off(Light light) {
        light.setState(OFF.getInstance());
        System.out.println("Light OFF!");
    }
}
