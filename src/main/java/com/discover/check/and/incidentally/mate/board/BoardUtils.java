package com.discover.check.and.incidentally.mate.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SIXTH_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    private BoardUtils() {
        throw new RuntimeException("Error instantiating this class");
    }

    public static boolean isValidTileCoordinate(int coordinate){
        return coordinate >= 0 && coordinate < 63;
    }

}
