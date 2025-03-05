package Match.Team.Player;

import Match.Team.Player.Score.BattingScoreCard;
import Match.Team.Player.Score.BowlingScoreCard;

import java.security.PublicKey;

public class PlayerDetails {
    public Person person;
    public PlayerType playerType;
    public BattingScoreCard battingScoreCard;
    public BowlingScoreCard bowlingScoreCard;

    public PlayerDetails(Person person, PlayerType playerType) {
        this.person = person;
        this.playerType = playerType;
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();


    }

    public void printBattingScoreCard() {
        System.out.println("PlayerName: " + person.name + " -- totalRuns: " + battingScoreCard.totalRuns
                + " -- totalBallsPlayed: " + battingScoreCard.totalBallsPlayed + " -- 4s: " + battingScoreCard.totalFours
                + " -- 6s: " + battingScoreCard.totalSixes + " -- outby: " +   ((battingScoreCard.wicketDetails != null) ? battingScoreCard.wicketDetails.takeBy.person.name : "notout"));
    }

    public void printBowlingScoreCard() {
        System.out.println("PlayerName: " + person.name + " -- totalOversThrown: " + bowlingScoreCard.totalOvers
                + " -- totalRunsGiven: " + bowlingScoreCard.totalRunsGiven + " -- WicketsTaken: " + bowlingScoreCard.wicketsTaken);
    }

}
