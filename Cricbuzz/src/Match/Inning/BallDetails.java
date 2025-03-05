package Match.Inning;

import Match.ScoreUpdater.BattingScoreUpdater;
import Match.ScoreUpdater.BowlingScoreUpdater;
import Match.ScoreUpdater.ScoreUpdaterObserver;
import Match.Team.Player.PlayerDetails;
import Match.Team.Team;
import Match.Team.Wicket;
import Match.Team.WicketType;

import java.util.ArrayList;
import java.util.List;

public class BallDetails {
    public int ballNumber;
    public BallType ballType;
    public PlayerDetails playedBy;
    public PlayerDetails bowledBy;
    public RunType runType;
    public Wicket wicket;
    public WicketType wicketType;
    public List<ScoreUpdaterObserver> scoreUpdaterObserverList = new ArrayList<>();

    public BallDetails(int ballNumber) {
        this.ballNumber = ballNumber;
        scoreUpdaterObserverList.add(new BattingScoreUpdater());
        scoreUpdaterObserverList.add(new BowlingScoreUpdater());
    }

    public void startBallDelivery(Team battingTeam, Team bowlingTeam, OverDetails over) {
        this.playedBy=battingTeam.getStriker();
        this.bowledBy=bowlingTeam.getCurrentBowler();
        this.ballType=BallType.NORMAL; //assuming evry ball is normal

        if(isWicketTaken()){
            this.runType = RunType.ZERO;
            //considering only BOLD
            wicket = new Wicket(WicketType.BOWLED, bowlingTeam.getCurrentBowler(), over,this);
            //making only striker out for now
            battingTeam.setStriker(null);
        }
        else {
            this.runType=getRunType();
            if(runType == RunType.ONE || runType == RunType.THREE) {
                //swap striket and non striker
                PlayerDetails temp = battingTeam.getStriker();
                battingTeam.setStriker(battingTeam.getNonStriker());
                battingTeam.setNonStriker(temp);
            }
        }
        notifyUpdaters(this);

    }

    private void notifyUpdaters(BallDetails ballDetails) {
        for(ScoreUpdaterObserver observer : scoreUpdaterObserverList) {
            observer.update(ballDetails);
        }

    }

    private RunType getRunType() {
        double val = Math.random();
        if (val <= 0.2) {
            return RunType.ONE;
        } else if (val >= 0.3 && val <= 0.5) {
            return RunType.TWO;
        } else if (val >= 0.6 && val <= 0.8) {
            return RunType.FOUR;
        } else {
            return RunType.SIX;
        }


    }

    private boolean isWicketTaken() {
        if (Math.random() < 0.2) {
            return true;
        } else {
            return false;
        }

    }
}
