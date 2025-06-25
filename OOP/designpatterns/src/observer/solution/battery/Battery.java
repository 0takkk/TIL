package observer.solution.battery;

public class Battery extends Subject {

    private int level = 100;

    public void consume(int amount) {
        level -= amount;
        this.notifyObservers();
    }

    public int getLevel() {
        return level;
    }
}
