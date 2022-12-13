module com.pong_game {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pong_game to javafx.fxml;
    exports com.pong_game;
}
