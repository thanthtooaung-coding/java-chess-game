package com.chess.chessgame;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HelloController {

    @FXML
    private GridPane gridPane;

    private final Board chessBoard = new Board();

    private int selectedRow = -1;
    private int selectedCol = -1;

    @FXML
    public void initialize() {
        int size = 8;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle square = new Rectangle(50, 50);
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.BEIGE);
                } else {
                    square.setFill(Color.BROWN);
                }

                int finalRow = row;
                int finalCol = col;
                square.setOnMouseClicked(e -> handleSquareClick(finalRow, finalCol));
                gridPane.add(square, col, row);

                Piece piece = chessBoard.getPieceAt(row, col);
                if (piece != null) {
                    Text pieceText = new Text(piece.isWhite() ? "W" + piece.name.charAt(0) : "B" + piece.name.charAt(0));
                    gridPane.add(pieceText, col, row);
                }
            }
        }
    }

    private boolean isWhiteTurn = true;

    private void handleSquareClick(int row, int col) {
        if (selectedRow == -1 && selectedCol == -1) {
            Piece piece = chessBoard.getPieceAt(row, col);
            if (piece != null && piece.isWhite() == isWhiteTurn) {
                selectedRow = row;
                selectedCol = col;
                highlightValidMoves(row, col, piece);
            }
        } else {
            Piece selectedPiece = chessBoard.getPieceAt(selectedRow, selectedCol);
            if (selectedPiece.isValidMove(selectedRow, selectedCol, row, col, chessBoard.getBoard())) {
                chessBoard.movePiece(selectedRow, selectedCol, row, col);
                isWhiteTurn = !isWhiteTurn;
            }
            selectedRow = -1;
            selectedCol = -1;
            gridPane.getChildren().clear();
            initialize();
        }
    }

    private void highlightValidMoves(int row, int col, Piece piece) {
        Piece[][] board = chessBoard.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.isValidMove(row, col, i, j, board)) {
                    Rectangle highlight = new Rectangle(50, 50, Color.LIGHTGREEN);
                    highlight.setOpacity(0.5);
                    gridPane.add(highlight, j, i);
                }
            }
        }
    }
}
