package com.discover.check.and.incidentally.mate.pieces;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.board.BoardUtils;
import com.discover.check.and.incidentally.mate.board.moves.AttackMove;
import com.discover.check.and.incidentally.mate.board.moves.MajorMove;
import com.discover.check.and.incidentally.mate.board.moves.Move;

import javax.print.MultiDocPrintJob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9 ,16};

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    // TODO: Implement the promotion of the pawn
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
                     * And because pawns cannot jump like knights and the pawn is planning to move two tiles, we have to make
                     * sure that the tile in front of the pawn is empty.
                     */
                    final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                    if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                       !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            /*
             * This here will be two edge cases for the pawn, first is when the currentCandidateOffset is 7, the pawn
             * is white, and it is lying on the eight column.
             *   a b c d e f g h
             * 8 0 1 2 3 - - - - 8
             * 7 - - - - - - - - 7
             * 6 - - - - - - - - 6
             * 5 - - - - - - - - 5
             * 4 - - - - - - - - 4
             * 3 - - - - - - - - 3
             * 2 - - - - - - - P 2
             * 1 - - - - - - - - 1
             *   a b c d e f g h
             * The very first tile is in the top left corner, and it keeps increasing.
             * The pawn will be moving to the left (.i.e. decrementing because we multiplied the candidate offset by -1)
             * If we add -7 to the pawn's position it will end up on a2 which is wrong
             */
            }else if(currentCandidateOffset == 7 &&
                    ((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())) ||
                     (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())){
                    final Piece pieceOnCandidate = board.getTile(currentCandidateOffset).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        // TODO: Implement promotion if the this move lays the piece on the end of the board
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
            /*
             * Same condition is applied for candidateOffset of 9
             */
            }else if(currentCandidateOffset == 9 &&
                    ((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())) ||
                    (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())){
                    final Piece pieceOnCandidate = board.getTile(currentCandidateOffset).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        // TODO: Implement promotion if the this move lays the piece on the end of the board
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
            }
        }

        return null;
    }
}
