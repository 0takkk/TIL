package command.problem.step2.button;

import command.problem.step2.alarm.Alarm;

// 버튼을 눌렀을 때, 다른 기능(알람 시작)으로 변경하고자 하면,
// Button 클래스의 코드 수정이 필요
// OCP 위배
public class Button {

    private Alarm alarm;

    public Button(Alarm alarm) {
        this.alarm = alarm;
    }

    public void press() {
        alarm.start();
    }
}
