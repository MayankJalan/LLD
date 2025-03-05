package Match.ScoreUpdater;

import Match.Inning.BallDetails;

public interface ScoreUpdaterObserver {

    public void update(BallDetails ballDetails);

}
