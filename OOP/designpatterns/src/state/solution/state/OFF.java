package state.solution.state;

import state.solution.light.Light;

public class OFF implements State {

    private static OFF off = new OFF();

    private OFF() {}

    public static OFF getInstance() {
        return off;
    }

    @Override
    public void on(Light light) {
        light.setState(ON.getInstance());
        System.out.println("Light ON!");
    }

    @Override
    public void off(Light light) {
        System.out.println("반응 없음");
    }
}
