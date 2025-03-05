package Match.Team;

import Match.Team.Player.PlayerBattingController;
import Match.Team.Player.PlayerBowlingController;
import Match.Team.Player.PlayerDetails;

import java.util.List;
import java.util.Queue;

public class Team {
    String name;
    Queue<PlayerDetails> playing11;
    PlayerBattingController battingController;
    PlayerBowlingController bowlingController;
    public boolean isWinner;

    public Team(String name, Queue<PlayerDetails> players, List<PlayerDetails> bowlers) {
        this.name = name;
        this.playing11=players;
        this.battingController = new PlayerBattingController(players);
        this.bowlingController = new PlayerBowlingController(bowlers);
    }

    public String getTeamName() {
        return this.name;
    }

    public void chooseNextBatsman() throws Exception {
        this.battingController.getNextPlayer();
    }

    public void chooseNextBowler(int maxOverCountPerBowler) {
        this.bowlingController.getNextBowler(maxOverCountPerBowler);
    }

    public PlayerDetails getStriker() {
        return this.battingController.getStriker();
    }

    public PlayerDetails getNonStriker() {
        return this.battingController.getNonStriker();
    }
    public void setStriker(PlayerDetails player) {
        battingController.setStriker(player);
    }

    public void setNonStriker(PlayerDetails player) {
        battingController.setNonStriker(player);
    }
    public PlayerDetails getCurrentBowler() {
        return bowlingController.getCurrentBowler();
    }
    public void printBattingScoreCard(){
        for(PlayerDetails playerDetails : playing11){
            playerDetails.printBattingScoreCard();
        }
    }
    public void printBowlingScoreCard(){

        for(PlayerDetails playerDetails : playing11){
            if(playerDetails.bowlingScoreCard.totalOvers > 0) {
                playerDetails.printBowlingScoreCard();
            }
        }
    }
    public int getTotalRuns(){
        int totalRun=0;
        for (PlayerDetails player :  playing11){
            totalRun+=player.battingScoreCard.totalRuns;
        }
        return totalRun;
    }





}

