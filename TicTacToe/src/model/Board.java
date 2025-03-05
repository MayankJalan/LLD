package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    int size;
    public PlayingPeice board[][];

    public Board(int size) {
        this.size = size;
        board =new PlayingPeice[size][size];
    }

    public int getSize() {
        return size;
    }

    public boolean addPiece(int x, int y, PlayingPeice playingPeice){
        if(board[x][y] != null){
            return false;
        }
        board[x][y]=playingPeice;
        return true;
    }

    public List<Coordinate> getFreeCells() {
        List<Coordinate> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new Coordinate(i, j));
                }
            }
        }

        return freeCells;
    }
    public void printBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }


}