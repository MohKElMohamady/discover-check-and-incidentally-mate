package com.discover.check.and.incidentally.mate.board;

import com.discover.check.and.incidentally.mate.pieces.Piece;

public final class EmptyTile extends Tile{

    public EmptyTile(final int tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
