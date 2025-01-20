package com.chess.chessgame;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite, "Knight");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        return (dx == 2 && dy == 1 || dx == 1 && dy == 2) &&
                (board[endX][endY] == null || board[endX][endY].isWhite() != this.isWhite());
    }
}
