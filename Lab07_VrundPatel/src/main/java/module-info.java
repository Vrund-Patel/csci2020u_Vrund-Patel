module com.example.lab07_vrundpatel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.lab07_vrundpatel to javafx.fxml;
    exports com.example.lab07_vrundpatel;
}