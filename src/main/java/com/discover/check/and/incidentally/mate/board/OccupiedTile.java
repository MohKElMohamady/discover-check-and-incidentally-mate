package com.discover.check.and.incidentally.mate.board;

import com.discover.check.and.incidentally.mate.pieces.Piece;

public final class OccupiedTile extends Tile {

    private final Piece pieceOnTile;

    public OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }
}
