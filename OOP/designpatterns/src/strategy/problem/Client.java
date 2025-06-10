package strategy.problem;

import strategy.problem.robot.Atom;
import strategy.problem.robot.Robot;
import strategy.problem.robot.TaekwonV;

public class Client {
    public static void main(String[] args) {
        Robot taekwonV = new TaekwonV("태권V");
        Robot atom = new Atom("아톰");

        System.out.println("My name is " + taekwonV.getName());
        taekwonV.move();
        taekwonV.attack();

        System.out.println();

        System.out.println("My name is " + atom.getName());
        atom.move();
        atom.attack();
    }
}
