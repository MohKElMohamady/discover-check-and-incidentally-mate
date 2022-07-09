package com.discover.check.and.incidentally.mate.board.moves;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.pieces.Piece;

public class MajorMove extends Move{
    public MajorMove(final Board board,final Piece movedPiece,final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
