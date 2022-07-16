package com.discover.check.and.incidentally.mate.pieces;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.board.moves.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    public Piece(final int piecePosition,final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // TODO:
        this.isFirstMove = false;
    }

    /**
     * Calculates the possible list of moves for a piece, will be unique for each piece
     */
    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }
    public boolean isFirstMove(){
        return this.isFirstMove;
    }
    public int getPiecePosition(){
        return this.piecePosition;
    }
    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
