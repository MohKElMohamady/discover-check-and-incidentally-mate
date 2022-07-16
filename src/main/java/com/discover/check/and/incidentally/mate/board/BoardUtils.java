package com.discover.check.and.incidentally.mate.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SIXTH_COLUMN = initColumn(5);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);
    /*
     * The two array below will be for the pawn, placed as a placeholder
     */
    public static final boolean[] SECOND_ROW = null;
    public static final boolean[] SEVENTH_ROW = null;
    public static final int NUM_TILES = 64;
    private static final int NUM_TILES_PER_ROW = 8;
    private BoardUtils() {
        throw new RuntimeException("Error instantiating this class");
    }
    /**
     * Declares an array of boolean of 64, takes one parameter which is the column number.
     * In our boards, we have 8 columns from 0 to 7
     * It keeps setting true to all the tiles of the column given to it
     */
    private static boolean[] initColumn(int columnNumber){
        final boolean[] column = new boolean[NUM_TILES];
        do{
            column[columnNumber] = true;
            columnNumber+= NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return column;
    }



    public static boolean isValidTileCoordinate(int coordinate){
        return coordinate >= 0 && coordinate < 63;
    }

}
