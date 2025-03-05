public class Main {
    public static void main(String args[]) {
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        String winner=game.startGame();
        System.out.println("game winner is: " + winner);
    }
}
