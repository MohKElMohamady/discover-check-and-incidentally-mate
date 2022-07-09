package com.discover.check.and.incidentally.mate.board.moves;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.pieces.Piece;

public class AttackMove extends Move{

    final Piece attackedPiece;

    public AttackMove(final Board board,final Piece movedPiece,final int destinationCoordinate, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }
}
