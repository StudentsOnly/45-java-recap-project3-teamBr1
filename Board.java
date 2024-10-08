import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int size;
    private char[][] grid;
    private List<Ghost> ghosts = new ArrayList<>();
    private Random random = new Random();
    private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;


    public enum DifficultyLevel {
        EASY, NORMAL, HARD
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Board(int size, DifficultyLevel difficultyLevel) {
        this.size = size;
        grid = new char[size][size];
        this.difficultyLevel = difficultyLevel;
        initializeBoard();

    }

    void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '.';
            }
        }

        int ghostCount;
        int powerupsCount;
        Ghost.Behaviour ghostBehaviour;


        switch (difficultyLevel) {
            case EASY:
                ghostCount = 3;
                ghostBehaviour = Ghost.Behaviour.NORMAL;
                powerupsCount = 4;
                break;
            case NORMAL:
                ghostCount = 4;
                ghostBehaviour = Ghost.Behaviour.FAST;
                powerupsCount = 3;
                break;
            case HARD:
                ghostCount = 5;
                ghostBehaviour = Ghost.Behaviour.TELEPORTING;
                powerupsCount = 2;
                break;
            default:
                ghostCount = 3;
                ghostBehaviour = Ghost.Behaviour.NORMAL;
                powerupsCount = 4;
                break;
        }

        placeRandomGhosts(ghostCount, ghostBehaviour); // Place 3 ghosts
        placeRandom('*', powerupsCount); // Place 2 power-ups
    }

    private void placeRandomGhosts(int count, Ghost.Behaviour behaviour) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (grid[x][y] != '.');
            ghosts.add(new Ghost(x, y, this, behaviour)); // Create and add new ghost to the list
        }
    }

    private void placeRandom(char ch, int count) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (grid[x][y] != '.');
            grid[x][y] = ch;
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCell(int x, int y, char ch) {
        grid[x][y] = ch;
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public boolean isGameOver(int pacManX, int pacManY) {
        return grid[pacManX][pacManY] == 'G';
    }

    public void moveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move(); // Each ghost moves independently
        }
    }

    public int getSize() {
        return size;
    }
}
