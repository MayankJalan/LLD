package Match.Team.Player;

import java.util.*;
import java.util.concurrent.DelayQueue;

public class PlayerBowlingController {

    Deque<PlayerDetails> bowlersList;
    PlayerDetails currentBowler;
    Map<PlayerDetails,Integer> bowlerVsOverCount;

    public PlayerBowlingController(List<PlayerDetails> bowlers) {
        setBowlersList(bowlers);
    }

    private void setBowlersList(List<PlayerDetails> bowlers) {
        this.bowlersList=new LinkedList<>();
        bowlerVsOverCount = new HashMap<>();
        for (PlayerDetails bowler : bowlers) {
            this.bowlersList.addLast(bowler);
            bowlerVsOverCount.put(bowler, 0);
        }
    }

    public void getNextBowler(int maxOverCountPerBowler){
        PlayerDetails nextBowler=this.bowlersList.pollFirst();
        if(this.bowlerVsOverCount.get(nextBowler)+1 == maxOverCountPerBowler){
            currentBowler=nextBowler;
        }
        else{
            bowlersList.addLast(nextBowler);
            currentBowler=nextBowler;
            bowlerVsOverCount.put(nextBowler,bowlerVsOverCount.get(nextBowler)+1);
        }
    }
    public PlayerDetails getCurrentBowler(){
        return currentBowler;
    }



}
