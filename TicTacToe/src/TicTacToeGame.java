import model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;
    public void initializeGame() {

        players=new LinkedList<>();
        Player player1=new Player("Mayank",new PlayingPieceX());
        Player player2=new Player("Kush", new PlayingPieceO());
        players.add(player1);
        players.add(player2);
        gameBoard =new Board(3);
    }


    public String startGame() {
        Scanner in=new Scanner(System.in);
        String winnerPlayer ="tie";
        while(true) {
            Player playerTurn=players.removeFirst();
            gameBoard.printBoard();
            List<Coordinate> freeCells= gameBoard.getFreeCells();
            if(freeCells.isEmpty()){
                break;
            }
            System.out.print("Player:" + playerTurn.getName() + " Enter row,column: ");
            int x=in.nextInt();
            int y=in.nextInt();
            boolean pieceAddedSuccessfully= gameBoard.addPiece(x,y,playerTurn.getPlayingPeice());
            if(!pieceAddedSuccessfully){
                System.out.println("Cell not empty, pls try again");
                players.addFirst(playerTurn);
                continue;
            }
            boolean winner = isThereWinner(x, y, playerTurn.getPlayingPeice());
            if(winner){
                gameBoard.printBoard();  // Print the final board after the winner is found
                System.out.println(playerTurn.getName() + " wins!");
                winnerPlayer= playerTurn.getName();
                break;
            }
            else
                players.addLast(playerTurn);

        }
        return winnerPlayer;
    }
    public boolean isThereWinner(int x, int y, PlayingPeice playingPeice) {
        int size = gameBoard.getSize();

        // Check the entire row at position x
        boolean rowMatch = true;
        for (int i = 0; i < size; i++) {
            if (gameBoard.board[x][i] != playingPeice) {
                rowMatch = false;
                break;
            }
        }

        // Check the entire column at position y
        boolean columnMatch = true;
        for (int i = 0; i < size; i++) {
            if (gameBoard.board[i][y] != playingPeice) {
                columnMatch = false;
                break;
            }
        }

        // Check the main diagonal only if (x, y) is on it
        boolean diagonalMatch = true;
        if (x == y) {
            for (int i = 0; i < size; i++) {
                if (gameBoard.board[i][i] != playingPeice) {
                    diagonalMatch = false;
                    break;
                }
            }
        } else {
            diagonalMatch = false;
        }

        // Check the anti-diagonal only if (x, y) is on it
        boolean antiDiagonalMatch = true;
        if (x + y == size - 1) {
            for (int i = 0; i < size; i++) {
                if (gameBoard.board[i][size - i - 1] != playingPeice) {
                    antiDiagonalMatch = false;
                    break;
                }
            }
        } else {
            antiDiagonalMatch = false;
        }

        // Return true if any of the win conditions are met
        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
