package command.problem.step3.button;

import command.problem.step3.alarm.Alarm;
import command.problem.step3.lamp.Lamp;

// 버튼을 눌렀을 때의 기능 변경을 위해 Button 클래스의 코드 수정이 필요
public class Button {

    private Lamp lamp;
    private Alarm alarm;
    private Mode mode;

    public Button(Lamp lamp, Alarm alarm, Mode mode) {
        this.lamp = lamp;
        this.alarm = alarm;
        this.mode = mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void press() {
        switch (mode) {
            case LAMP -> lamp.turnOn();
            case ALARM -> alarm.start();
        }
    }
}
