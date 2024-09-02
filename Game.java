import java.util.Scanner;

public class Game {
    private Board board;
    private PacMan pacMan;
    private boolean gameOver = false;

    public Game() {
        board = new Board(10);
        pacMan = new PacMan(board);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            board.printBoard();
            System.out.println("Score: " + pacMan.getScore());
            System.out.println("Move (U/D/L/R): ");
            char move = scanner.next().toUpperCase().charAt(0);

            pacMan.move(move);
            board.moveGhosts();
            checkGameState();
            pacMan.updateInvincibility();
        }

        System.out.println("Game Over! Final Score: " + pacMan.getScore());
    }

    private void checkGameState() {
        if (board.isGameOver(pacMan.getX(), pacMan.getY()) && !pacMan.isInvincible()) {
            gameOver = true;
        }
    }
}
