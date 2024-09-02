import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int size;
    private char[][] grid;
    private List<Ghost> ghosts = new ArrayList<>();
    private Random random = new Random();

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '.';
            }
        }

        placeRandomGhosts(3); // Place 3 ghosts
        placeRandom('*', 2); // Place 2 power-ups
    }

    private void placeRandomGhosts(int count) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (grid[x][y] != '.');
            ghosts.add(new Ghost(x, y, this)); // Create and add new ghost to the list
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
