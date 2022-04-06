module com.example.lab09_vrundpatel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.lab09_vrundpatel to javafx.fxml;
    exports com.example.lab09_vrundpatel;
}