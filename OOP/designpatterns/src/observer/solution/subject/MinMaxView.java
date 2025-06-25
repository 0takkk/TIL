package observer.solution.subject;

import java.util.Collections;
import java.util.List;

public class MinMaxView implements Observer {

    private ScoreRecord scoreRecord;

    public MinMaxView(ScoreRecord scoreRecord) {
        this.scoreRecord = scoreRecord;
    }

    @Override
    public void update() {
        List<Integer> scores = scoreRecord.getScores();
        this.displayMinMax(scores);
    }

    private void displayMinMax(List<Integer> scores) {
        int min = Collections.min(scores, null);
        int max = Collections.max(scores, null);

        System.out.println("MIN : " + min + ", MAX : " + max);
    }
}
