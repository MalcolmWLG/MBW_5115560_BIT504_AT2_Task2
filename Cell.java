import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cell {
    // Content of this cell (empty, cross, nought)
    Player content;
    // Row and column position of this cell
    int row, col;

    // Colors for symbols
    private static final Color CROSS_COLOR = Color.RED;
    private static final Color NOUGHT_COLOR = Color.BLUE;

    /** Constructor to initialize cell with the specified row and column */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        clear(); // Initialize cell as empty
    }

    /** Paint the cell's content on the graphics canvas */
    public void paint(Graphics g) {
        // Cast Graphics to Graphics2D for enhanced drawing features
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(GameMain.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Calculate top-left corner coordinates for drawing
        int x1 = col * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
        int y1 = row * GameMain.CELL_SIZE + GameMain.CELL_PADDING;

        if (content == Player.Cross) {
            // Draw an "X" for Cross player
            g2d.setColor(CROSS_COLOR);
            int x2 = (col + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
            int y2 = (row + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
            g2d.drawLine(x1, y1, x2, y2); // Diagonal from top-left to bottom-right
            g2d.drawLine(x2, y1, x1, y2); // Diagonal from top-right to bottom-left
        } else if (content == Player.Nought) {
            // Draw an "O" for Nought player
            g2d.setColor(NOUGHT_COLOR);
            g2d.drawOval(x1, y1, GameMain.SYMBOL_SIZE, GameMain.SYMBOL_SIZE);
        }
    }

    /** Clear this cell's content */
    public void clear() {
        content = Player.Empty; // Set content to Empty
    }
}
