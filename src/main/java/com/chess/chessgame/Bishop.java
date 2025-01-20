package com.chess.chessgame;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite, "Bishop");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        if (dx == dy) {
            int xDirection = (endX - startX) / dx;
            int yDirection = (endY - startY) / dy;
            for (int i = 1; i < dx; i++) {
                if (board[startX + i * xDirection][startY + i * yDirection] != null) {
                    return false;
                }
            }
            return board[endX][endY] == null || board[endX][endY].isWhite() != this.isWhite();
        }
        return false;
    }
}
