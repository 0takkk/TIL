package strategy.solution.robot;

import strategy.solution.robot.attack.AttackStrategy;
import strategy.solution.robot.move.MovingStrategy;

public class Atom extends Robot {

    public Atom(String name, MovingStrategy movingStrategy, AttackStrategy attackStrategy) {
        super(name, movingStrategy, attackStrategy);
    }
}
