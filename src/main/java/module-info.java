module ca.cmpt213 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ca.cmpt213 to javafx.fxml;
    exports ca.cmpt213;
}
