module ca.cmpt213 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ca.cmpt213.asn4.tictactoe.ui to javafx.fxml;
    exports ca.cmpt213.asn4.tictactoe.ui;
    exports ca.cmpt213.asn4.tictactoe.game;
}
