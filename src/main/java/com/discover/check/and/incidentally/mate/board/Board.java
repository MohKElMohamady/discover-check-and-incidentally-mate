package com.discover.check.and.incidentally.mate.board;

import com.discover.check.and.incidentally.mate.pieces.Alliance;
import com.discover.check.and.incidentally.mate.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

public class Board {

    private final List<Tile> gameBoard;
    private Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
    }
    public Tile getTile(final int tileCoordinate){
        return null;
    }

    private static List<Tile> createGameBoard(Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for(int i=0; i < BoardUtils.NUM_TILES; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }
    private static Board createInitialBoard(){
        return null;
    }
    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        public Builder(){

        }
        public Board build(){
            return new Board(this);
        }
        public Builder setPiece(Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = this.nextMoveMaker;
            return this;
        }
    }
}
