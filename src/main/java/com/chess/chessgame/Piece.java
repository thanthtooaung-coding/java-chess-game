package com.chess.chessgame;

public abstract class Piece {
    protected boolean isWhite;
    protected String name;

    public Piece(boolean isWhite, String name) {
        this.isWhite = isWhite;
        this.name = name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);
}
