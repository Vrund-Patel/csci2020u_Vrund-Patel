module com.example.lab10_vrundpatel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.lab10_vrundpatel to javafx.fxml;
    exports com.example.lab10_vrundpatel;
}