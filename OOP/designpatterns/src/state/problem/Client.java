package state.problem;

public class Client {

    public static void main(String[] args) {
        Light light = new Light();
        light.off();
        light.on();
        light.off();
    }
}
