package com.chess.chessgame;

public class Board {
    private final Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupBoard();
    }

    private void setupBoard() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
            board[6][i] = new Pawn(true);
        }
        // Rooks
        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);
        // Knights
        board[0][1] = new Knight(false);
        board[0][6] = new Knight(false);
        board[7][1] = new Knight(true);
        board[7][6] = new Knight(true);
        // Bishops
        board[0][2] = new Bishop(false);
        board[0][5] = new Bishop(false);
        board[7][2] = new Bishop(true);
        board[7][5] = new Bishop(true);
        // Queens
        board[0][3] = new Queen(false);
        board[7][3] = new Queen(true);
        // Kings
        board[0][4] = new King(false);
        board[7][4] = new King(true);
    }

    public Piece getPieceAt(int x, int y) {
        return board[x][y];
    }

    public void movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startX][startY];
        board[endX][endY] = board[startX][startY];
        board[startX][startY] = null;

        if (piece instanceof Pawn && (endX == 0 || endX == 7)) {
            board[endX][endY] = new Queen(piece.isWhite());
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public boolean isKingInCheck(boolean isWhite) {
        int kingX = -1, kingY = -1;

        // Find the King
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King && piece.isWhite() == isWhite) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.isWhite() != isWhite && piece.isValidMove(i, j, kingX, kingY, board)) {
                    return true;
                }
            }
        }
        return false;
    }
}
