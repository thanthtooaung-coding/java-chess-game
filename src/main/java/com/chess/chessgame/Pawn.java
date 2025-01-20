package com.chess.chessgame;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite, "Pawn");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int direction = isWhite ? -1 : 1;
        if (startY == endY) {

            if (endX == startX + direction && board[endX][endY] == null) {
                return true;
            }

            return (startX == 1 || startX == 6) && endX == startX + 2 * direction && board[endX][endY] == null;
        } else if (Math.abs(startY - endY) == 1 && endX == startX + direction) {
            return board[endX][endY] != null && board[endX][endY].isWhite() != this.isWhite();
        }
        return false;
    }
}
