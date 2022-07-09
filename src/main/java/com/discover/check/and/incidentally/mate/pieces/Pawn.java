package com.discover.check.and.incidentally.mate.pieces;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.board.BoardUtils;
import com.discover.check.and.incidentally.mate.board.moves.MajorMove;
import com.discover.check.and.incidentally.mate.board.moves.Move;

import javax.print.MultiDocPrintJob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16};

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            int candidateDestinationCoordinate = this.piecePosition + (currentCandidateOffset * this.pieceAlliance.getDirection());
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }
            /*
             * This condition will check if the white pawn is lying on the second row or if the black pawn is lying on
             * the seventh row, if so, they are eligible to do the double jump.
             */
            else if(this.isFirstMove && currentCandidateOffset == 16 &&
                    (BoardUtils.SECOND_ROW[candidateDestinationCoordinate] && this.pieceAlliance.isWhite()) ||
                    (BoardUtils.SEVENTH_ROW[candidateDestinationCoordinate] && this.pieceAlliance.isBlack())){
                /*
                 * And because pawns cannot jump like pawns and the pawn is planning to move two moves, we have to make
                 * sure that the tile in front of the pawn is empty.
                 */
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                   !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            }
        }

        return null;
    }
}
