import java.util.*;
import java.util.Scanner;

public class Game {
    private Board board;
    private PacMan pacMan;
    private boolean gameOver = false;
    private Board.DifficultyLevel difficultyLevel = Board.DifficultyLevel.EASY;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start the Pac-Man Game...");
        System.out.println("Choose Difficulty Level:");
        System.out.println("Easy (E)");
        System.out.println("Normal (N)");
        System.out.println("Hard (H)");
        char level = scanner.next().toUpperCase().charAt(0);
        switch (level) {
            case 'E':
                difficultyLevel = Board.DifficultyLevel.EASY;
                System.out.println("Level set successfully");
                //board.printBoard();
                break;
            case 'N':
                difficultyLevel = Board.DifficultyLevel.NORMAL;
                System.out.println("Level set successfully");
                //board.printBoard();
                break;
            case 'H':
                difficultyLevel = Board.DifficultyLevel.HARD;
                System.out.println("Level set successfully");
                // board.printBoard();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }

        board = new Board(10, difficultyLevel);
        pacMan = new PacMan(board);

        while (!gameOver) {
            board.printBoard();
            System.out.println("Score: " + pacMan.getScore());
            System.out.println("Move (W/S/A/D): ");
            System.out.println("Restart (R)");
            System.out.println("Quit (Q)");
            char option = scanner.next().toUpperCase().charAt(0);
            char move;

           // scanner.nextLine();  // Consume newline
            switch (option) {
                case 'W', 'A', 'S', 'D':
                    move = option;
                    pacMan.move(move);
                    board.moveGhosts();
                    checkGameState();
                    pacMan.updateInvincibility();
                    break;
                case 'R':
                    System.out.println("Restarting the game...");
                    board = new Board(10, difficultyLevel);
                    pacMan = new PacMan(board);
                    break;
                case 'Q':
                    System.out.println("Quiting the game...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        System.out.println("Game Over! Final Score: " + pacMan.getScore());
    }

    private void checkGameState() {
        if (board.isGameOver(pacMan.getX(), pacMan.getY()) && !pacMan.isInvincible()) {
            gameOver = true;
        }
    }
}
