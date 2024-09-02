import java.util.Random;

public class Ghost {
    private int x, y;
    private Board board;
    private Random random = new Random();
    private char previousCell = '.'; // To remember what was on the cell before the ghost moved there
    private Behaviour behaviour = Behaviour.NORMAL;

    enum Behaviour {
        NORMAL, FAST, TELEPORTING
    }

    public Ghost(int x, int y, Board board, Behaviour behaviour) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.behaviour = behaviour;
        board.setCell(x, y, 'G');
    }

    public void move() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] direction = directions[random.nextInt(directions.length)];
        int newX;
        int newY;
        switch (behaviour) {
            default -> {
                newX = x + direction[0];
                newY = y + direction[1];
            }
            case TELEPORTING -> {
                newX = random.nextInt(board.getSize());
                newY = random.nextInt(board.getSize());
            }
            case FAST ->  {
                newX = x + 2 * direction[0];
                newY = y + 2 * direction[1];
            }
        }

        if (board.isWithinBounds(newX, newY) && board.getCell(newX, newY) != 'G') {
            // Restore the previous cell's state before the ghost moved
            board.setCell(x, y, previousCell);

            // Remember the new cell's state before the ghost moves there
            previousCell = board.getCell(newX, newY);

            // Move the ghost to the new position
            x = newX;
            y = newY;
            board.setCell(x, y, 'G');
        }
    }

    // Getters and setters for x and y can be added as needed.
}
