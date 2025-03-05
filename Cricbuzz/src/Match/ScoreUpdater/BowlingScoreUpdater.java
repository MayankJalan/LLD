package Match.ScoreUpdater;

import Match.Inning.BallDetails;
import Match.Inning.BallType;
import Match.Inning.RunType;

public class BowlingScoreUpdater implements ScoreUpdaterObserver{
    @Override
    public void update(BallDetails ballDetails) {
        int run=0;
        if (RunType.ONE == ballDetails.runType) {
            run = 1;
        } else if (RunType.TWO == ballDetails.runType) {
            run = 2;
        } else if (RunType.FOUR == ballDetails.runType) {
            run = 4;
        } else if (RunType.SIX == ballDetails.runType) {
            run = 6;
        }
        ballDetails.bowledBy.bowlingScoreCard.totalRunsGiven+=run;

        if(ballDetails.wicket != null){
            ballDetails.bowledBy.bowlingScoreCard.wicketsTaken++;
        }
        if (ballDetails.ballType == BallType.NOBALL) {
            ballDetails.bowledBy.bowlingScoreCard.noBallCount++;
        }
        if (ballDetails.ballType == BallType.WIDEBALL) {
            ballDetails.bowledBy.bowlingScoreCard.wideBallCount++;
        }

    }
}
