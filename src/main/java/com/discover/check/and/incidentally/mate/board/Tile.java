package com.discover.check.and.incidentally.mate.board;

import com.discover.check.and.incidentally.mate.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/*
 * We will be representing the board as of a 64 long array instead of doing a 8 by 8 two-dimensional array
 * We will be creating 64 tiles and enumerate them from 0 to 63 to capture all tiles on the board
 */
public abstract class Tile {
    private final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();


    public Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /*
     * This method returns if the current tile is empty or occupied
     */
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    /**
     * Creates the 64 empty tiles upfront so that they can be used as cache and it will be added to a map where the key
     * is the tile number and the value is the EmptyTile itself
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i=0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }
    /**
     * Checks if the passed piece is null, if so it returns an empty tile from the already cached empty tile map,
     * otherwise create a new OccupiedTile with the piece on it
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece == null ? EMPTY_TILES.get(tileCoordinate) : new OccupiedTile(tileCoordinate, piece);
    }
}
