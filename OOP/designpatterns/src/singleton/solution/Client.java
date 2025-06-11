package singleton.solution;

import singleton.solution.staticprinter.StaticPrinterUserThread;
import singleton.solution.synchronizedprinter.SynchronizedPrinterUserThread;

public class Client {

    private static final int USER_NUMBER = 5;

    public static void main(String[] args) throws InterruptedException {
        StaticPrinterUserThread[] staticPrinterUserThreads = new StaticPrinterUserThread[USER_NUMBER];
        for(int i = 0; i < USER_NUMBER; i++) {
            staticPrinterUserThreads[i] = new StaticPrinterUserThread((i+1) + "-static-user-thread");
            staticPrinterUserThreads[i].start();
        }

        Thread.sleep(100);

        SynchronizedPrinterUserThread[] synchronizedPrinterUserThreads = new SynchronizedPrinterUserThread[USER_NUMBER];
        for(int i = 0; i < USER_NUMBER; i++) {
            synchronizedPrinterUserThreads[i] = new SynchronizedPrinterUserThread((i+1) + "-synchronized-user-thread");
            synchronizedPrinterUserThreads[i].start();
        }
    }
}
