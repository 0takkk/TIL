package state.problem.light;

public class Light {

    private static int ON = 0;  // 형광등이 켜진 상태
    private static int OFF = 1;  // 형광등이 꺼진 상태
    private static int SLEEPING = 2;  // 취침등 상태 추가

    private int state;  // 형광등의 현재 상태

    public Light() {
        state = OFF;
    }

    public void on() {
        if(state == ON) {
            System.out.println("취침등 On!");
            state = SLEEPING;
        }
        else if(state == SLEEPING) {
            System.out.println("Light On!");
            state = ON;
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
        else if (state == SLEEPING) {
            System.out.println("Light OFF!");
            state = OFF;
        }
        else {
            System.out.println("Light OFF!");
            state = OFF;
        }
    }
}
