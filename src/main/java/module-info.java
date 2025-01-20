module com.chess.chessgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.chess.chessgame to javafx.fxml;
    exports com.chess.chessgame;
}