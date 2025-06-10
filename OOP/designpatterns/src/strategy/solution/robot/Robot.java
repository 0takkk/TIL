package strategy.solution.robot;

import strategy.solution.robot.attack.AttackStrategy;
import strategy.solution.robot.move.MovingStrategy;

public abstract class Robot {

    private String name;

    private MovingStrategy movingStrategy;

    private AttackStrategy attackStrategy;

    public Robot(String name, MovingStrategy movingStrategy, AttackStrategy attackStrategy) {
        this.name = name;
        this.movingStrategy = movingStrategy;
        this.attackStrategy = attackStrategy;
    }

    public String getName() {
        return name;
    }

    public void move() {
        movingStrategy.move();
    }

    public void attack() {
        attackStrategy.attack();
    }
}
