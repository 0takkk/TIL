package state.problem;

public class Light {

    private static int ON = 0;  // 형광등이 켜진 상태
    private static int OFF = 1;  // 형광등이 꺼진 상태

    private int state;  // 형광등의 현재 상태

    public Light() {
        state = OFF;
    }

    public void on() {
        if(state == ON) {
            System.out.println("반응 없음");
        }
        else {
            System.out.println("Light On!");
            state = ON;
        }
    }

    public void off() {
        if(state == OFF) {
            System.out.println("반응 없음");
        }
        else {
            System.out.println("Light OFF!");
            state = OFF;
        }
    }
}
