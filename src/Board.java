import java.util.ArrayList;

public class Board {
    int height;
    int width;
    BoardPosition[][] grid;
    ArrayList<Block> blocks;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new BoardPosition[height][width];

        // initialize board positions
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new BoardPosition(i, j, null); // initially no tile
            }
        }

        blocks = new ArrayList<>();
    }

    public BoardPosition getBoardPosition(Tile tile) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].tile == tile) {
                    return grid[i][j];
                }
            }
        }
        return null; // tile not found
    }

    public void placeTile(Tile tile, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[y][x].tile = tile;
        }
    }

    public void updateBoard() {
        System.out.println("updating board");
    
        // clear the grid (remove all tile references)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x].tile = null; // reset all board positions
            }
        }
    
        // reassign tiles to correct positions
        for (Block block : blocks) {
            for (Tile tile : block.tiles) {
                BoardPosition pos = getBoardPosition(tile);
                if (pos != null) {
                    grid[pos.y][pos.x].tile = tile; // place tile in new position
                }
            }
        }
    
        System.out.println("board updated.");
    }
    
}
