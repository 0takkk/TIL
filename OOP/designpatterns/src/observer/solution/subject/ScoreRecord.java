package observer.solution.subject;

import java.util.ArrayList;
import java.util.List;

public class ScoreRecord extends Subject {

    private List<Integer> scores = new ArrayList<>();

    public void addScore(int score) {
        scores.add(score);

        // 데이터 변경되면 각 옵저버에게 데이터의 변경을 통보
        notifyObservers();
    }

    public List<Integer> getScores() {
        return scores;
    }
}
