package com.chess.chessgame;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ChessController {

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
        gridPane.getChildren().removeIf(node -> node instanceof Rectangle && ((Rectangle) node).getFill() == Color.LIGHTGREEN);

        if (selectedRow == -1 && selectedCol == -1) {
            Piece piece = chessBoard.getPieceAt(row, col);
            if (piece != null && piece.isWhite() == isWhiteTurn) {
                selectedRow = row;
                selectedCol = col;

                Rectangle selectedSquare = new Rectangle(50, 50, Color.YELLOW);
                selectedSquare.setOpacity(0.5);
                gridPane.add(selectedSquare, col, row);

                highlightValidMoves(row, col, piece);
            }
        } else {
            Piece selectedPiece = chessBoard.getPieceAt(selectedRow, selectedCol);
            if (selectedPiece.isValidMove(selectedRow, selectedCol, row, col, chessBoard.getBoard())) {
                Piece capturedPiece = chessBoard.movePiece(selectedRow, selectedCol, row, col);
                if (capturedPiece != null) {
                    addCapturedPiece(capturedPiece);
                }
                isWhiteTurn = !isWhiteTurn;
                checkGameEnd();
            }
            selectedRow = -1;
            selectedCol = -1;
            gridPane.getChildren().clear();
            initialize();
        }
    }

    @FXML
    private Pane highlightLayer;
    private void highlightValidMoves(int row, int col, Piece piece) {
        Piece[][] board = chessBoard.getBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.isValidMove(row, col, i, j, board)) {
                    Rectangle highlight = new Rectangle(50, 50, Color.LIGHTGREEN);
                    highlight.setOpacity(0.5);
                    highlight.setMouseTransparent(true);
                    gridPane.add(highlight, j, i);
                }
            }
        }
    }

    @FXML
    private HBox whiteCapturedPieces;

    @FXML
    private HBox blackCapturedPieces;

    private void addCapturedPiece(Piece piece) {
        Text capturedPieceText = new Text(piece.name.charAt(0) + "");
        if (piece.isWhite()) {
            whiteCapturedPieces.getChildren().add(capturedPieceText);
        } else {
            blackCapturedPieces.getChildren().add(capturedPieceText);
        }
    }

    private void checkGameEnd() {
        boolean whiteInCheck = chessBoard.isKingInCheck(true);
        boolean blackInCheck = chessBoard.isKingInCheck(false);

        boolean whiteHasMoves = chessBoard.hasValidMoves(true);
        boolean blackHasMoves = chessBoard.hasValidMoves(false);

        if (!whiteHasMoves && whiteInCheck) {
            showGameEndMessage("Black wins by checkmate!");
        } else if (!blackHasMoves && blackInCheck) {
            showGameEndMessage("White wins by checkmate!");
        } else if (!whiteHasMoves || !blackHasMoves) {
            showGameEndMessage("Stalemate!");
        }
    }

    private void showGameEndMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        resetGame();
    }

    private void resetGame() {
        chessBoard.reset();
        isWhiteTurn = true;
        selectedRow = -1;
        selectedCol = -1;
        gridPane.getChildren().clear();
        initialize();
    }
}
