package com.chess.chessgame;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite, "King");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        return dx <= 1 && dy <= 1 &&
                (board[endX][endY] == null || board[endX][endY].isWhite() != this.isWhite());
    }
}
