import models.Board;
import models.Cell;
import models.Dice;
import models.Player;

import java.sql.SQLOutput;
import java.util.Deque;
import java.util.LinkedList;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;


    Game(){
        initializeGame();

    }

    private void initializeGame() {
        board=new Board(10,7,4);
        dice=new Dice(2);
        Player player1=new Player("Mayank",0);
        Player player2=new Player("Kush",0);
        playersList.addFirst(player2);
        playersList.addFirst(player1);
    }



    public void startGame() {

        while(winner==null){
            Player playerTurn=playersList.removeFirst();
            System.out.println("player turn is:" + playerTurn.name + " current position is: " + playerTurn.currentPosition);


            int total= dice.rollDice();
            System.out.println("Dice total " + total);

            int playerNewPosition=playerTurn.currentPosition+total;
            playerNewPosition=jumpCheck(playerNewPosition);
            playerTurn.currentPosition=playerNewPosition;

            System.out.println("player "+playerTurn.name+ " new position is: " + playerTurn.currentPosition+"\n");
            if(playerNewPosition >= board.cells.length * board.cells.length-1){
                winner = playerTurn;
            }
            playersList.addLast(playerTurn);

        }

        System.out.println("Winner is "+winner.getName());
    }

    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition > board.cells.length * board.cells.length-1 ){
            return playerNewPosition;
        }

        Cell cell=board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start==playerNewPosition){
            String jumpBy = (cell.jump.start < cell.jump.end)? "ladder" : "snake";
            System.out.println("Jump Done by " +jumpBy );
            return cell.jump.end;
        }
        return playerNewPosition;

    }
}
