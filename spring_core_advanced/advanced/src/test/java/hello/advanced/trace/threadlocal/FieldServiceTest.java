package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    public void field() {
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread theadA = new Thread(userA);
        theadA.setName("thread-A");
        Thread theadB = new Thread(userB);
        theadB.setName("thread-B");

        theadA.start();
        sleep(2000);  // 동시성 문제 발생 X
        theadB.start();

        sleep(3000);  // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    @Test
    public void field_concurrency() {
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread theadA = new Thread(userA);
        theadA.setName("thread-A");
        Thread theadB = new Thread(userB);
        theadB.setName("thread-B");

        theadA.start();
        sleep(100);  // 동시성 문제 발생 O
        theadB.start();

        sleep(3000);  // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
