package models;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public Cell [][]cells;
    public Board(int boardSize, int numberOfSnakes, int numberOfLadders){
        initializeCells(boardSize);
        addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void addSnakesLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders) {
        while(numberOfSnakes>0){
            int start= ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int end=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(end>=start){
                continue;
            }
            Jump jump=new Jump(start,end);
            Cell cell=getCell(start);
            cell.jump=jump;
            numberOfSnakes--;
        }

        while(numberOfSnakes>0){
            int start= ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int end=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(end<=start){
                continue;
            }
            Jump jump=new Jump(start,end);
            Cell cell=getCell(start);
            cell.jump=jump;
            numberOfSnakes--;
        }
    }

    public Cell getCell(int start) {
        int i=start/cells.length;
        int j=start% cells.length;
        return cells[i][j];
    }

    private void initializeCells(int boardSize) {
        cells=new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                cells[i][j]=new Cell();
            }
    }


}
}
