package strategy.solution;


import strategy.solution.robot.Atom;
import strategy.solution.robot.Robot;
import strategy.solution.robot.TaekwonV;
import strategy.solution.robot.attack.MissileStrategy;
import strategy.solution.robot.attack.PunchStrategy;
import strategy.solution.robot.move.FlyingStrategy;
import strategy.solution.robot.move.WalkingStrategy;

public class Client {

    public static void main(String[] args) {
        Robot taekwonV = new TaekwonV("태권V", new WalkingStrategy(), new MissileStrategy());
        Robot atom = new Atom("아톰", new FlyingStrategy(), new PunchStrategy());

        System.out.println("My name is " + taekwonV.getName());
        taekwonV.move();
        taekwonV.attack();

        System.out.println();

        System.out.println("My name is " + atom.getName());
        atom.move();
        atom.attack();
    }
}
