package com.discover.check.and.incidentally.mate.pieces;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.board.BoardUtils;
import com.discover.check.and.incidentally.mate.board.Tile;
import com.discover.check.and.incidentally.mate.board.moves.AttackMove;
import com.discover.check.and.incidentally.mate.board.moves.MajorMove;
import com.discover.check.and.incidentally.mate.board.moves.Move;
import com.google.common.collect.ImmutableList;

import java.util.LinkedList;
import java.util.List;

public final class Bishop extends Piece{

    private static int[] CANDIDATE_LEGAL_MOVES = {-9, -7, 7, 9};

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    /**
     * The bishop will start sliding on the board until either:
     * 1) It reaches the end of the board and will move out of the board
     * 2) It can take on a piece
     * 3) Finds a piece of the same alliance
     */
    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new LinkedList<>();
        for(final int candidateCoordinateOffset : CANDIDATE_LEGAL_MOVES){
            int candidateDestinationCoordinate = this.piecePosition;
            /*
             * We have to keep moving in diagonals until we reach the end of the board
             */
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                candidateDestinationCoordinate += candidateCoordinateOffset;
                /*
                 * Break out if we reach the end of the board
                 */
                if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                   isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
                    break;
                }
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestinationTile = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestinationTile.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestinationTile));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 7);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
