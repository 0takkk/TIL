package singleton.problem;

import singleton.problem.multithread.UserThread;
import singleton.problem.singlethread.User;

public class Client {

    private static final int USER_NUMBER = 5;

    public static void main(String[] args) {
        User[] users = new User[USER_NUMBER];
        for(int i = 0; i < USER_NUMBER; i++) {
            users[i] = new User((i+1) + "-user");
            users[i].print();
        }

        UserThread[] userThreads = new UserThread[USER_NUMBER];
        for(int i = 0; i < USER_NUMBER; i++) {
            userThreads[i] = new UserThread((i+1) + "-user-thread");
            userThreads[i].start();
        }
    }
}
