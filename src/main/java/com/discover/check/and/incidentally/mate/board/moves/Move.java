package com.discover.check.and.incidentally.mate.board.moves;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(final Board board,final Piece movedPiece,final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }
}
