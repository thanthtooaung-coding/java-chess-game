package com.chess.chessgame;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite, "Rook");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Add rook-specific movement logic.
        return true;
    }
}
