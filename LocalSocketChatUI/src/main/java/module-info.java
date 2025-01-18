module com.example.localsocketchatui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.localsocketchatui to javafx.fxml;
    exports com.example.localsocketchatui;
}