package strategy.solution.robot.move;

public class WalkingStrategy implements MovingStrategy {

    @Override
    public void move() {
        System.out.println("I can only walk.");
    }
}
