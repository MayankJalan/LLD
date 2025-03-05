package Match.Inning;

import Match.MatchType;
import Match.Team.*;
import Match.Team.Player.PlayerDetails;

import java.util.ArrayList;
import java.util.List;

public class InningDetails {
    Team battingTeam;
    Team bowlingTeam;
    MatchType matchType;
    List<OverDetails> overs;

    public InningDetails(Team battingTeam, Team bowlingTeam, MatchType matchType) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.matchType = matchType;
        overs = new ArrayList<>();
    }

    public void start(int runsToWin) {
        try {
            battingTeam.chooseNextBatsman();
        } catch (Exception e) {

        }
        int noOfOvers = matchType.noOfOver();
        for (int overNumber = 1; overNumber <= noOfOvers; overNumber++) {
            bowlingTeam.chooseNextBowler(matchType.maxOversCountBowlers());
            OverDetails over = new OverDetails(overNumber, bowlingTeam.getCurrentBowler());
            overs.add(over);
            try {
                boolean won = over.startOver(battingTeam, bowlingTeam, runsToWin);

                if (won) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
            //swap striker and non striker
            PlayerDetails temp = battingTeam.getStriker();
            battingTeam.setStriker(battingTeam.getNonStriker());
            battingTeam.setNonStriker(temp);
        }
    }

    public int getTotalRuns() {
        return battingTeam.getTotalRuns();
    }
}

