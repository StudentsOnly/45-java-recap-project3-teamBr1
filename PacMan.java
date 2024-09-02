public class PacMan {
    private int x, y;
    private int score = 0;
    private boolean invincible = false;
    private int invincibleCounter = 0;
    private Board board;

    public PacMan(Board board) {
        this.board = board;
        this.x = board.getSize() / 2;
        this.y = board.getSize() / 2;
        board.setCell(x, y, 'P');
    }

    public void move(char direction) {
        int newX = x, newY = y;

        switch (direction) {
            case 'U':
                newX = x - 1;
                break;
            case 'D':
                newX = x + 1;
                break;
            case 'L':
                newY = y - 1;
                break;
            case 'R':
                newY = y + 1;
                break;
            default:
                System.out.println("Invalid move. Use U, D, L, R.");
                return;
        }

        if (board.isWithinBounds(newX, newY)) {
            char cell = board.getCell(newX, newY);

            if (cell == '.') {
                score++;
            } else if (cell == 'G') {
                if (invincible) {
                    score += 10; // Eating a ghost gives extra points
                } else {
                    board.setCell(x, y, ' '); // Clear old position
                    x = newX;
                    y = newY;
                    return;
                }
            } else if (cell == '*') {
                invincible = true;
                invincibleCounter = 5; // Invincible for 5 turns
            }

            board.setCell(x, y, ' '); // Clear old position
            x = newX;
            y = newY;
            board.setCell(x, y, 'P'); // Update new position
        } else {
            System.out.println("Can't move outside the board!");
        }
    }

    public void updateInvincibility() {
        if (invincible) {
            invincibleCounter--;
            if (invincibleCounter == 0) {
                invincible = false;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInvincible() {
        return invincible;
    }
}
