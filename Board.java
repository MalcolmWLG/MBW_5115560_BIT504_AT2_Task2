import java.awt.*;

public class Board {
    // Grid line width
    public static final int GRID_WIDTH = 8;
    // Grid line half width
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;

    // 2D array of ROWS-by-COLS Cell instances
    Cell[][] cells;

    /** Constructor to create the game board */
    public Board() {
        // Initialize the cells array
        cells = new Cell[GameMain.ROWS][GameMain.COLS];
        for (int row = 0; row < GameMain.ROWS; ++row) {
            for (int col = 0; col < GameMain.COLS; ++col) {
                cells[row][col] = new Cell(row, col); // Create a new Cell for each position
            }
        }
    }

    /** Clear the board by resetting all cells to empty */
    public void clear() {
        for (int row = 0; row < GameMain.ROWS; ++row) {
            for (int col = 0; col < GameMain.COLS; ++col) {
                cells[row][col].content = Player.Empty;
            }
        }
    }

    /** Return true if it is a draw (no more empty cells) */
    public boolean isDraw() {
        for (int row = 0; row < GameMain.ROWS; ++row) {
            for (int col = 0; col < GameMain.COLS; ++col) {
                if (cells[row][col].content == Player.Empty) {
                    return false; // Found an empty cell, not a draw
                }
            }
        }
        return true; // No empty cells, it's a draw
    }

    /** Check if the given player has won */
    public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
        // Check row, column, and diagonals
        return checkRow(thePlayer, playerRow) || checkColumn(thePlayer, playerCol) || checkDiagonals(thePlayer);
    }

    /** Helper to check if a row is filled with the same player's symbol */
    private boolean checkRow(Player thePlayer, int row) {
        for (int col = 0; col < GameMain.COLS; ++col) {
            if (cells[row][col].content != thePlayer) {
                return false; // Mismatch in the row
            }
        }
        return true;
    }

    /** Helper to check if a column is filled with the same player's symbol */
    private boolean checkColumn(Player thePlayer, int col) {
        for (int row = 0; row < GameMain.ROWS; ++row) {
            if (cells[row][col].content != thePlayer) {
                return false; // Mismatch in the column
            }
        }
        return true;
    }

    /** Helper to check if either diagonal is filled with the same player's symbol */
    private boolean checkDiagonals(Player thePlayer) {
        boolean mainDiagonal = true, antiDiagonal = true;
        for (int i = 0; i < GameMain.ROWS; ++i) {
            if (cells[i][i].content != thePlayer) {
                mainDiagonal = false;
            }
            if (cells[i][GameMain.COLS - 1 - i].content != thePlayer) {
                antiDiagonal = false;
            }
        }
        return mainDiagonal || antiDiagonal;
    }

    /**
     * Draws the grid and calls on each cell to paint itself
     */
    public void paint(Graphics g) {
        // Draw the grid lines
        g.setColor(Color.GRAY);
        for (int row = 1; row < GameMain.ROWS; ++row) {
            g.fillRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,
                    GameMain.CANVAS_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < GameMain.COLS; ++col) {
            g.fillRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0,
                    GRID_WIDTH, GameMain.CANVAS_HEIGHT);
        }

        // Paint each cell
        for (int row = 0; row < GameMain.ROWS; ++row) {
            for (int col = 0; col < GameMain.COLS; ++col) {
                cells[row][col].paint(g);
            }
        }
    }
}
