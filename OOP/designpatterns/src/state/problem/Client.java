package state.problem;

import state.problem.light.Light;

public class Client {

    public static void main(String[] args) {
        Light light = new Light();
        light.off();
        light.on();
        light.on();
        light.on();
        light.off();
    }
}
