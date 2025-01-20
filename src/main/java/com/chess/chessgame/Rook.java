package com.chess.chessgame;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite, "Rook");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if (startX != endX && startY != endY) {
            return false;
        }

        int xDirection = Integer.compare(endX, startX);
        int yDirection = Integer.compare(endY, startY);

        int x = startX + xDirection;
        int y = startY + yDirection;

        while (x != endX || y != endY) {
            if (board[x][y] != null) {
                return false;
            }
            x += xDirection;
            y += yDirection;
        }

        return board[endX][endY] == null || board[endX][endY].isWhite() != this.isWhite();
    }
}