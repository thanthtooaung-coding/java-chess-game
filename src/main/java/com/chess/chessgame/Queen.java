package com.chess.chessgame;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite, "Queen");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        Rook rookMovement = new Rook(this.isWhite);
        Bishop bishopMovement = new Bishop(this.isWhite);
        return rookMovement.isValidMove(startX, startY, endX, endY, board) ||
                bishopMovement.isValidMove(startX, startY, endX, endY, board);
    }
}
