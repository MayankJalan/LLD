package models;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int diceCount,min=1,max=6;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice(){
        int total=0;
        int diceUsed=0;
        while(diceUsed<diceCount){
            int number= ThreadLocalRandom.current().nextInt(min,max+1);
            total+=number;
            diceUsed++;
        }
        return total;
    }

}
