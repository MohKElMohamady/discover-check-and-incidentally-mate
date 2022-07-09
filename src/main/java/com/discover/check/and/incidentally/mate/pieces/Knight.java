package com.discover.check.and.incidentally.mate.pieces;

import com.discover.check.and.incidentally.mate.board.Board;
import com.discover.check.and.incidentally.mate.board.BoardUtils;
import com.discover.check.and.incidentally.mate.board.Move;
import com.discover.check.and.incidentally.mate.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    /*
     * These are all the possible candidate moves that a knight can make when it is on the board
     */
    private static int[] CANDIDATE_LEGAL_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        /*
         * For each legal move by the knight, we will add the current position of the knight to it, and we will see if
         * the tile is actually on the board and after that check if the tile is occupied or not
         */
        for(final int currentCandidateOffset : CANDIDATE_LEGAL_MOVES){
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            /*
             * We have to check if the knight lies on the first, second, sixth or seventh column
             */
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSixthColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset))
                    continue;
                /*
                * If we do not go out of the board and the destination tile is valid, we will fetch the tile from the
                * board
                */
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                /*
                 * Now we have to check if that fetched tile from board is not occupied or occupied
                 */
                if(!candidateDestinationTile.isTileOccupied()){
                    /*
                     * If it is not occupied, we need to return a move that will let the piece move on that tile
                     * TODO: Add the move to change the position of that piece to another tile
                     */
                    legalMoves.add(new Move());
                }
                /*
                 * If it is occupied, we need to check if the piece on that tile will is of the same alliance as this
                 * piece or not
                 */
                else{
                   final Piece pieceOnOccupiedTile = candidateDestinationTile.getPiece();
                   final Alliance pieceAlliance = pieceOnOccupiedTile.getPieceAlliance();
                   if(this.pieceAlliance != pieceAlliance){
                       /*
                        * This move should be responsible for taking out the other piece since they are of different
                        * alliance
                        * TODO: Add the move to take out the other piece of different alliance present on the occupied tile
                        */
                       legalMoves.add(new Move());
                   }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * There are some tiles on which the knight be present on it, adding the offset would lead to a wrong tile which are
     * First, Second, Sixth and Seventh Column
     */
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSixthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SIXTH_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);
    }
}
