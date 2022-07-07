/*
 * We will be representing the board as of a 64 long array instead of doing a 8 by 8 two dimensional array
 * We will be creating 64 tiles and enumerate them from 0 to 63 to capture all tiles on the board
 */
public abstract class Tile {
    private int tileCoordinate;

    public Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /*
     * This method returns if the current tile is empty or occupied
     */
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
}
